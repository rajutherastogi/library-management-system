package com.raju.libraryManagementSystem.controller;

import com.raju.libraryManagementSystem.entity.Book;
import com.raju.libraryManagementSystem.enums.Language;
import com.raju.libraryManagementSystem.service.CatalogueService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@Api(tags = "Catalogue APIs")
@RequestMapping("/book-catalogue")
@RestController
public class CatalogueController {
    private final CatalogueService catalogueService;

    @Autowired
    public CatalogueController(final CatalogueService catalogueService) {
        this.catalogueService = catalogueService;
    }

    @ApiOperation(value = "Fetch all books", notes = "Fetches all books from catalogue.")
    @GetMapping(path = "/books")
    Set<Book> fetchAllBooks() {
        return catalogueService.fetchAllBooks();
    }

    @ApiOperation(value = "Fetch books by title", notes = "Fetches books by title from catalogue.")
    @GetMapping(path = "/books/{title}")
    List<Book> searchBooksByTitle(@PathVariable final String title) {
        return catalogueService.searchBooksByTitle(title);
    }

    @ApiOperation(value = "Fetch books by author", notes = "Fetches books by author from catalogue.")
    @GetMapping(path = "/books/{authorName}")
    List<Book> searchBooksByAuthor(@PathVariable final String authorName) {
        return catalogueService.searchBooksByAuthor(authorName);
    }

    @ApiOperation(value = "Fetch books by subject", notes = "Fetches books by subject from catalogue.")
    @GetMapping(path = "/books/{subject}")
    List<Book> searchBooksBySubject(@PathVariable final String subject) {
        return catalogueService.searchBooksBySubject(subject);
    }

    @ApiOperation(value = "Fetch books by language", notes = "Fetches books by language from catalogue.")
    @GetMapping(path = "/books/{language}")
    List<Book> searchBooksByLanguage(@PathVariable final Language language) {
        return catalogueService.searchBooksByLanguage(language);
    }
}
