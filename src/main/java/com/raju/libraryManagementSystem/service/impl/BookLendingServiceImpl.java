package com.raju.libraryManagementSystem.service.impl;

import com.raju.libraryManagementSystem.constants.Constants;
import com.raju.libraryManagementSystem.constants.ErrorCodes;
import com.raju.libraryManagementSystem.entity.*;
import com.raju.libraryManagementSystem.enums.BookStatus;
import com.raju.libraryManagementSystem.enums.LendingStatus;
import com.raju.libraryManagementSystem.exception.UserErrorException;
import com.raju.libraryManagementSystem.repository.BookLendingRepository;
import com.raju.libraryManagementSystem.service.AccountManagementService;
import com.raju.libraryManagementSystem.service.BookLendingService;
import com.raju.libraryManagementSystem.service.BookManagementService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookLendingServiceImpl implements BookLendingService {
    private final BookManagementService bookManagementService;
    private final AccountManagementService accountManagementService;
    private final BookLendingRepository bookLendingRepository;

    public BookLendingServiceImpl(final BookManagementService bookManagementService, final AccountManagementService accountManagementService, final BookLendingRepository bookLendingRepository) {
        this.bookManagementService = bookManagementService;
        this.accountManagementService = accountManagementService;
        this.bookLendingRepository = bookLendingRepository;
    }

    @Override
    public BookItem issueBook(final BookLendingRequest bookLendingRequest) {
        String memberId = bookLendingRequest.getMemberId();
        String bookISBN = bookLendingRequest.getBookISBN();
        Optional<Account> optionalUser = accountManagementService.fetchAccount(memberId);
        if (!optionalUser.isPresent()) {
            throw new UserErrorException(HttpStatus.NOT_FOUND, ErrorCodes.ACCOUNT_NOT_FOUND.name(), ErrorCodes.ACCOUNT_NOT_FOUND.getMessage());
        }
        List<BookLendingRecord> bookLendingRecords = bookLendingRepository.fetchBookLendingRecordsForMember(memberId);
        if (bookLendingRecords.size() >= Constants.BOOK_ISSUE_LIMIT) {
            throw new UserErrorException(ErrorCodes.BOOK_ISSUE_LIMIT_REACHED.name(), ErrorCodes.BOOK_ISSUE_LIMIT_REACHED.getMessage());
        }
        Optional<BookItem> bookItemOptional = bookManagementService.fetchAllAvailableBookItemsOfBook(bookISBN).stream().findFirst();
        if (!bookItemOptional.isPresent()) {
            throw new UserErrorException(HttpStatus.NOT_FOUND, ErrorCodes.BOOK_NOT_AVAILABLE.name(), ErrorCodes.BOOK_NOT_AVAILABLE.getMessage());
        }
        BookItem availableBookItem = bookItemOptional.get();
        bookLendingRepository.insertBookLendingRecord(BookLendingRecord.builder().bookItemBarcode(availableBookItem.getBarcode())
                .memberId(memberId).creationDate(LocalDate.now()).dueDate(LocalDate.now().plusDays(Constants.MAX_LENDING_DAYS))
                .status(LendingStatus.ISSUED).build());
        availableBookItem.setBookStatus(BookStatus.LOANED);
        bookManagementService.updateBookItem(availableBookItem.getBarcode(), availableBookItem);
        return availableBookItem;
    }

    @Override
    public BookItem returnBook(final BookReturningRequest bookReturningRequest) {
        String memberId = bookReturningRequest.getMemberId();
        String bookItemBarcode = bookReturningRequest.getBookItemBarcode();
        Optional<BookLendingRecord> bookLendingRecordOptional = bookLendingRepository.fetchBookLendingRecordForMemberAndBookItem(memberId, bookItemBarcode);
        if (!bookLendingRecordOptional.isPresent()) {
            throw new UserErrorException(HttpStatus.NOT_FOUND, ErrorCodes.BOOK_LENDING_RECORD_NOT_FOUND.name(), ErrorCodes.BOOK_LENDING_RECORD_NOT_FOUND.getMessage());
        }
        Optional<BookItem> bookItemIssuedOptional = bookManagementService.fetchBookItemByBarcode(bookItemBarcode);
        if (!bookItemIssuedOptional.isPresent()) {
            throw new UserErrorException(HttpStatus.NOT_FOUND, ErrorCodes.BOOK_ITEM_NOT_FOUND.name(), ErrorCodes.BOOK_ITEM_NOT_FOUND.getMessage());
        }
        BookItem bookItemIssued = bookItemIssuedOptional.get();
        bookItemIssued.setBookStatus(BookStatus.AVAILABLE);
        bookManagementService.updateBookItem(bookItemIssued.getBarcode(), bookItemIssued);
        BookLendingRecord bookLendingRecord = bookLendingRecordOptional.get();
        bookLendingRecord.setReturnDate(LocalDate.now());
        bookLendingRecord.setStatus(LendingStatus.RETURNED);
        bookLendingRepository.updateBookLendingRecord(bookLendingRecord.getMemberId(), bookLendingRecord.getBookItemBarcode(), bookLendingRecord);
        return bookItemIssued;
    }

    @Override
    public List<BookItem> fetchBooksIssued(final String memberId) {
        List<BookItem> bookItemList = new ArrayList<>();
        List<BookLendingRecord> bookLendingRecords = bookLendingRepository.fetchBookLendingRecordsForMember(memberId);
        bookLendingRecords.forEach(bookLendingRecord -> bookItemList.add(bookManagementService.fetchBookItemByBarcode(bookLendingRecord.getBookItemBarcode()).orElseThrow(() ->
                new UserErrorException(HttpStatus.NOT_FOUND, ErrorCodes.BOOK_ITEM_NOT_FOUND.name(), ErrorCodes.BOOK_ITEM_NOT_FOUND.getMessage()))));
        return bookItemList;
    }

    @Override
    public List<BookLendingRecord> fetchExistingBookLendingRecords() {
        return bookLendingRepository.fetchExistingBookLendingRecords();
    }
}
