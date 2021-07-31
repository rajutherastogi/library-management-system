package com.raju.libraryManagementSystem.entity;

import com.raju.libraryManagementSystem.enums.LendingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookLendingRecord {
    private LocalDate creationDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private String bookItemBarcode;
    private String memberId;
    private LendingStatus status;
}
