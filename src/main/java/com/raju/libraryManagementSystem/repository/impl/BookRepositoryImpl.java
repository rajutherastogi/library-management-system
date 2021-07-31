package com.raju.libraryManagementSystem.repository.impl;

import com.raju.libraryManagementSystem.entity.Book;
import com.raju.libraryManagementSystem.entity.BookItem;
import com.raju.libraryManagementSystem.repository.BookRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class BookRepositoryImpl implements BookRepository {
    private final Map<String, Book> bookMap;
    private final Map<String, BookItem> bookItemMap;

    public BookRepositoryImpl() {
        this.bookMap = new HashMap<>();
        this.bookItemMap = new HashMap<>();
    }

    @Override
    public Book insertBook(final Book book) {
        return bookMap.put(book.getISBN(), book);
    }

    @Override
    public BookItem insertBookItem(final BookItem bookItem) {
        return bookItemMap.put(bookItem.getBarcode(), bookItem);
    }

    @Override
    public Book updateBook(final String ISBN, final Book book) {
        return bookMap.put(ISBN, book);
    }

    @Override
    public BookItem updateBookItem(final String barcode, final BookItem bookItem) {
        return bookItemMap.put(barcode, bookItem);
    }

    @Override
    public Optional<Book> fetchBookByISBN(final String ISBN) {
        return Optional.of(bookMap.get(ISBN));
    }

    @Override
    public Optional<BookItem> fetchBookItemByBarcode(final String barcode) {
        return Optional.of(bookItemMap.get(barcode));
    }

    @Override
    public Set<Book> fetchAllBooks() {
        return bookMap.values().stream().collect(Collectors.toSet());
    }

    @Override
    public Set<BookItem> fetchAllBookItems() {
        return bookItemMap.values().stream().collect(Collectors.toSet());
    }
}
