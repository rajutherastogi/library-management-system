package com.raju.libraryManagementSystem.service;

import com.raju.libraryManagementSystem.entity.Book;
import com.raju.libraryManagementSystem.entity.BookItem;

import java.util.Optional;
import java.util.Set;

public interface BookManagementService {
    Book addBook(final Book book);

    BookItem addBookItem(final BookItem bookItem);

    Set<Book> fetchExistingBooks();

    Set<BookItem> fetchExistingBookItems();

    Set<BookItem> fetchAllAvailableBookItemsOfBook(final String ISBN);

    Book updateBook(final String ISBN, final Book book);

    BookItem updateBookItem(final String barcode, final BookItem bookItem);

    Optional<Book> fetchBookByISBN(final String ISBN);

    Optional<BookItem> fetchBookItemByBarcode(final String barcode);
}
