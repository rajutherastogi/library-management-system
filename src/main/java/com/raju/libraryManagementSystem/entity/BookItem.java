package com.raju.libraryManagementSystem.entity;

import com.raju.libraryManagementSystem.enums.BookFormat;
import com.raju.libraryManagementSystem.enums.BookStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookItem{
    @NotBlank
    private String ISBN;
    @NotBlank
    private String barcode;
    @NotNull
    private Date issueDate;
    @NotNull
    private Date dueDate;
    @NotNull
    private double price;
    @NotNull
    private BookFormat bookFormat;
    @NotNull
    private BookStatus bookStatus;
    @NotNull
    private Date purchaseDate;
    @NotNull
    private Date publicationDate;
    @Valid
    private Rack placedAt;
}
