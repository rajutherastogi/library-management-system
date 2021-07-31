package com.raju.libraryManagementSystem.service;

import com.raju.libraryManagementSystem.entity.BookItem;
import com.raju.libraryManagementSystem.entity.BookLendingRecord;
import com.raju.libraryManagementSystem.entity.BookLendingRequest;
import com.raju.libraryManagementSystem.entity.BookReturningRequest;

import java.util.List;

public interface BookLendingService {
    BookItem issueBook(final BookLendingRequest bookLendingRequest);

    BookItem returnBook(final BookReturningRequest bookReturningRequest);

    List<BookItem> fetchBooksIssued(final String memberId);

    List<BookLendingRecord> fetchExistingBookLendingRecords();
}
