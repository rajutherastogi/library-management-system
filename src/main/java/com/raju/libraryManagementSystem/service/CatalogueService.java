package com.raju.libraryManagementSystem.service;

import com.raju.libraryManagementSystem.entity.Book;
import com.raju.libraryManagementSystem.enums.Language;

import java.util.List;
import java.util.Set;

public interface CatalogueService {
    Set<Book> fetchAllBooks();

    List<Book> searchBooksByTitle(final String title);

    List<Book> searchBooksByAuthor(final String authorName);

    List<Book> searchBooksBySubject(final String subject);

    List<Book> searchBooksByLanguage(final Language language);
}
