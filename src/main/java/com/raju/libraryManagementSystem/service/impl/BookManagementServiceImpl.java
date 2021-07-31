package com.raju.libraryManagementSystem.service.impl;

import com.raju.libraryManagementSystem.constants.ErrorCodes;
import com.raju.libraryManagementSystem.entity.Book;
import com.raju.libraryManagementSystem.entity.BookItem;
import com.raju.libraryManagementSystem.enums.BookStatus;
import com.raju.libraryManagementSystem.exception.UserErrorException;
import com.raju.libraryManagementSystem.repository.BookRepository;
import com.raju.libraryManagementSystem.service.BookManagementService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookManagementServiceImpl implements BookManagementService {
    private final BookRepository bookRepository;

    @Autowired
    public BookManagementServiceImpl(final BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book addBook(final Book book) {
        if (fetchBookByISBN(book.getISBN()).isPresent()) {
            throw new UserErrorException(HttpStatus.CONFLICT, ErrorCodes.BOOK_ALREADY_EXISTS.name(), ErrorCodes.BOOK_ALREADY_EXISTS.getMessage());
        }
        return bookRepository.insertBook(book);
    }

    @Override
    public BookItem addBookItem(final BookItem bookItem) {
        if (fetchBookItemByBarcode(bookItem.getBarcode()).isPresent()) {
            throw new UserErrorException(HttpStatus.CONFLICT, ErrorCodes.BOOK_ITEM_ALREADY_EXISTS.name(), ErrorCodes.BOOK_ITEM_ALREADY_EXISTS.getMessage());
        }
        return bookRepository.insertBookItem(bookItem);
    }

    @Override
    public Set<Book> fetchExistingBooks() {
        return bookRepository.fetchAllBooks();
    }

    @Override
    public Set<BookItem> fetchExistingBookItems() {
        return bookRepository.fetchAllBookItems();
    }

    @Override
    public Set<BookItem> fetchAllAvailableBookItemsOfBook(final String ISBN) {
        return bookRepository.fetchAllBookItems().stream()
                .filter(bookItem -> (StringUtils.equals(bookItem.getISBN(), ISBN) && bookItem.getBookStatus() == BookStatus.AVAILABLE))
                .collect(Collectors.toSet());
    }

    @Override
    public Book updateBook(final String ISBN, final Book book) {
        return bookRepository.updateBook(ISBN, book);
    }

    @Override
    public BookItem updateBookItem(final String barcode, final BookItem bookItem) {
        return bookRepository.updateBookItem(barcode, bookItem);
    }

    @Override
    public Optional<Book> fetchBookByISBN(final String ISBN) {
        return bookRepository.fetchBookByISBN(ISBN);
    }

    @Override
    public Optional<BookItem> fetchBookItemByBarcode(final String barcode) {
        return bookRepository.fetchBookItemByBarcode(barcode);
    }
}
