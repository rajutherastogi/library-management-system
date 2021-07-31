package com.raju.libraryManagementSystem.service.impl;

import com.raju.libraryManagementSystem.entity.Book;
import com.raju.libraryManagementSystem.entity.Person;
import com.raju.libraryManagementSystem.enums.Language;
import com.raju.libraryManagementSystem.service.BookManagementService;
import com.raju.libraryManagementSystem.service.CatalogueService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CatalogueServiceImpl implements CatalogueService {
    private final BookManagementService bookManagementService;

    @Autowired
    public CatalogueServiceImpl(final BookManagementService bookManagementService) {
        this.bookManagementService = bookManagementService;
    }

    @Override
    public Set<Book> fetchAllBooks() {
        return bookManagementService.fetchExistingBooks();
    }

    @Override
    public List<Book> searchBooksByTitle(final String title) {
        return bookManagementService.fetchExistingBooks().stream()
                .filter(book -> StringUtils.equals(book.getTitle(), title)).collect(Collectors.toList());
    }

    @Override
    public List<Book> searchBooksByAuthor(final String authorName) {
        return bookManagementService.fetchExistingBooks().stream()
                .filter(book -> isAuthor(authorName, book.getAuthors())).collect(Collectors.toList());
    }

    @Override
    public List<Book> searchBooksBySubject(final String subject) {
        return bookManagementService.fetchExistingBooks().stream()
                .filter(book -> StringUtils.equals(book.getSubject(), subject)).collect(Collectors.toList());
    }

    @Override
    public List<Book> searchBooksByLanguage(final Language language) {
        return bookManagementService.fetchExistingBooks().stream()
                .filter(book -> (book.getLanguage() == language)).collect(Collectors.toList());
    }

    private boolean isAuthor(final String authorName, final List<Person> authors) {
        return authors.stream().anyMatch(author -> StringUtils.equals(author.getName(), authorName));
    }
}
