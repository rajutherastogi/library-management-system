package com.raju.libraryManagementSystem.repository;

import com.raju.libraryManagementSystem.entity.BookLendingRecord;

import java.util.List;
import java.util.Optional;

public interface BookLendingRepository {
    BookLendingRecord insertBookLendingRecord(final BookLendingRecord bookLendingRecord);

    List<BookLendingRecord> fetchBookLendingRecordsForMember(final String memberId);

    Optional<BookLendingRecord> fetchBookLendingRecordForMemberAndBookItem(final String memberId, final String barcode);

    List<BookLendingRecord> fetchExistingBookLendingRecords();

    BookLendingRecord updateBookLendingRecord(String memberId, String bookItemBarCode, BookLendingRecord bookLendingRecord);
}
