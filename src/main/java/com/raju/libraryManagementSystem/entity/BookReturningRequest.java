package com.raju.libraryManagementSystem.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookReturningRequest {
    @NotBlank
    private String memberId;
    @NotBlank
    private String bookItemBarcode;
}
