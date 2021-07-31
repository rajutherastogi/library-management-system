package com.raju.libraryManagementSystem.controller;

import com.raju.libraryManagementSystem.entity.Book;
import com.raju.libraryManagementSystem.entity.BookItem;
import com.raju.libraryManagementSystem.service.BookManagementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = "Book Manager APIs")
@RequestMapping("/book-manager")
@RestController
public class BookManagementController {
    private final BookManagementService bookManagementService;

    @Autowired
    public BookManagementController(final BookManagementService bookManagementService) {
        this.bookManagementService = bookManagementService;
    }

    @ApiOperation(value = "Add a book", notes = "Adds a book to catalogue.")
    @PostMapping(path = "/books", consumes = "application/json", produces = "application/json")
    public Book addBook(@Valid @RequestBody final Book book) {
        return bookManagementService.addBook(book);
    }

    @ApiOperation(value = "Add a book iItem", notes = "Adds a book iItem to catalogue.")
    @PostMapping(path = "/bookItems", consumes = "application/json", produces = "application/json")
    public BookItem addBookItem(@Valid @RequestBody final BookItem bookItem) {
        return bookManagementService.addBookItem(bookItem);
    }

    @ApiOperation(value = "Update a book", notes = "Updates a book.")
    @PutMapping(path = "/books/{ISBN}", consumes = "application/json", produces = "application/json")
    public Book updateBook(@PathVariable final String ISBN, @Valid @RequestBody final Book book) {
        return bookManagementService.updateBook(ISBN, book);
    }

    @ApiOperation(value = "Update a book item", notes = "Adds a book iItem to catalogue.")
    @PutMapping(path = "/bookItems/{barcode}", consumes = "application/json", produces = "application/json")
    public BookItem updateBookItem(@PathVariable final String barcode, @Valid @RequestBody final BookItem bookItem) {
        return bookManagementService.updateBookItem(barcode, bookItem);
    }
}
