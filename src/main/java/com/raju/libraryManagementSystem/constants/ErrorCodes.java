package com.raju.libraryManagementSystem.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCodes {
    SERVER_ERROR("Some Server error occurred"),
    CONFLICT("Resource already exists."),
    INVALID_PARAMETER("Value of some parameter(s) is not valid"),
    INVALID_BODY("Error in parsing payload"),
    ACCOUNT_ALREADY_EXISTS("Account already exists."),
    INVALID_CREDENTIALS("Credentials are not valid."),
    ACCOUNT_NOT_FOUND("Account doesn't exist."),
    BOOK_ISSUE_LIMIT_REACHED("Books can't be issued beyond limit"),
    BOOK_NOT_AVAILABLE("Book is not available currently."),
    BOOK_LENDING_RECORD_NOT_FOUND("Book lending record doesn't exist."),
    BOOK_ITEM_NOT_FOUND("Book item doesn't exist."),
    BOOK_ALREADY_EXISTS("Book already exists."),
    BOOK_ITEM_ALREADY_EXISTS("Book item already exists.");

    private String message;
}
