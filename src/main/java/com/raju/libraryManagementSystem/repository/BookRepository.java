package com.raju.libraryManagementSystem.repository;

import com.raju.libraryManagementSystem.entity.Book;
import com.raju.libraryManagementSystem.entity.BookItem;

import java.util.Optional;
import java.util.Set;

public interface BookRepository {
    Book insertBook(final Book book);

    BookItem insertBookItem(final BookItem bookItem);

    Set<Book> fetchAllBooks();

    Set<BookItem> fetchAllBookItems();

    Book updateBook(final String ISBN, final Book book);

    BookItem updateBookItem(final String barcode, final BookItem bookItem);

    Optional<Book> fetchBookByISBN(final String ISBN);

    Optional<BookItem> fetchBookItemByBarcode(final String barcode);
}
