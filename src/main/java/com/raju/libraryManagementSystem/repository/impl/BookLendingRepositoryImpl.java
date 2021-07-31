package com.raju.libraryManagementSystem.repository.impl;

import com.raju.libraryManagementSystem.constants.ErrorCodes;
import com.raju.libraryManagementSystem.entity.BookLendingRecord;
import com.raju.libraryManagementSystem.exception.UserErrorException;
import com.raju.libraryManagementSystem.repository.BookLendingRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class BookLendingRepositoryImpl implements BookLendingRepository {
    private final List<BookLendingRecord> bookLendingRecords;

    public BookLendingRepositoryImpl() {
        this.bookLendingRecords = new ArrayList<>();
    }


    @Override
    public BookLendingRecord insertBookLendingRecord(final BookLendingRecord bookLendingRecord) {
        bookLendingRecords.add(bookLendingRecord);
        return bookLendingRecord;
    }

    @Override
    public List<BookLendingRecord> fetchBookLendingRecordsForMember(final String memberId) {
        return bookLendingRecords.stream()
                .filter(bookLendingRecord -> StringUtils.equals(bookLendingRecord.getMemberId(), memberId))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<BookLendingRecord> fetchBookLendingRecordForMemberAndBookItem(final String memberId, final String barcode) {
        return bookLendingRecords.stream()
                .filter(bookLendingRecord -> (StringUtils.equals(bookLendingRecord.getMemberId(), memberId) && StringUtils.equals(bookLendingRecord.getBookItemBarcode(), barcode)))
                .findFirst();
    }

    @Override
    public List<BookLendingRecord> fetchExistingBookLendingRecords() {
        return bookLendingRecords;
    }

    @Override
    public BookLendingRecord updateBookLendingRecord(final String memberId, final String bookItemBarCode, final BookLendingRecord bookLendingRecord) {
        Optional<BookLendingRecord> existingBookLendingRecordOptional =
                this.fetchBookLendingRecordForMemberAndBookItem(memberId, bookItemBarCode);

        if (!existingBookLendingRecordOptional.isPresent()) {
            throw new UserErrorException(HttpStatus.CONFLICT, ErrorCodes.BOOK_LENDING_RECORD_NOT_FOUND.name(), ErrorCodes.BOOK_LENDING_RECORD_NOT_FOUND.getMessage());
        }
        bookLendingRecords.remove(existingBookLendingRecordOptional.get());
        bookLendingRecords.add(bookLendingRecord);
        return bookLendingRecord;

    }
}
