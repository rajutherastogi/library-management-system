package com.raju.libraryManagementSystem.entity;

import com.raju.libraryManagementSystem.enums.Language;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {
    @NotBlank
    private String ISBN;
    @NotBlank
    private String title;
    @NotBlank
    private String subject;
    @NotBlank
    private String publisher;
    @NotNull
    private Language language;
    @Min(1)
    private int numberOfPages;
    @Size(min = 1)
    private List<Person> authors;
}
