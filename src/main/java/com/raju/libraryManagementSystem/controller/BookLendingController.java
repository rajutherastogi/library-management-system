package com.raju.libraryManagementSystem.controller;

import com.raju.libraryManagementSystem.entity.BookItem;
import com.raju.libraryManagementSystem.entity.BookLendingRequest;
import com.raju.libraryManagementSystem.entity.BookReturningRequest;
import com.raju.libraryManagementSystem.service.BookLendingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "Book Lending APIs")
@RequestMapping("/book-lender")
@RestController
public class BookLendingController {
    private final BookLendingService bookLendingService;

    @Autowired
    public BookLendingController(final BookLendingService bookLendingService) {
        this.bookLendingService = bookLendingService;
    }

    @ApiOperation(value = "Issue Book", notes = "Issues a book to a member.")
    @PostMapping(path = "/issue", consumes = "application/json", produces = "application/json")
    BookItem issueBook(@Valid @RequestBody final BookLendingRequest bookLendingRequest) {
        return bookLendingService.issueBook(bookLendingRequest);
    }

    @ApiOperation(value = "Return Book", notes = "Returns a book issued by a member.")
    @PostMapping(path = "/return", consumes = "application/json", produces = "application/json")
    BookItem returnBook(@Valid @RequestBody final BookReturningRequest bookReturningRequest) {
        return bookLendingService.returnBook(bookReturningRequest);
    }

    @ApiOperation(value = "Fetch books issued", notes = "Fetches all books issued by a member.")
    @GetMapping(path = "/booksIssued/{memberId}")
    List<BookItem> fetchBooksIssued(@PathVariable final String memberId) {
        return bookLendingService.fetchBooksIssued(memberId);
    }
}
