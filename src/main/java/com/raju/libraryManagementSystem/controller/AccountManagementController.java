package com.raju.libraryManagementSystem.controller;

import com.raju.libraryManagementSystem.entity.Account;
import com.raju.libraryManagementSystem.entity.BookItem;
import com.raju.libraryManagementSystem.entity.LoginCredential;
import com.raju.libraryManagementSystem.entity.Profile;
import com.raju.libraryManagementSystem.service.AccountManagementService;
import com.raju.libraryManagementSystem.service.BookLendingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Valid
@Api(tags = "Account Manager APIs")
@RequestMapping("/account-manager")
@RestController
public class AccountManagementController {
    private final AccountManagementService accountManagementService;
    private final BookLendingService bookLendingService;

    @Autowired
    public AccountManagementController(final AccountManagementService accountManagementService, final BookLendingService bookLendingService) {
        this.accountManagementService = accountManagementService;
        this.bookLendingService = bookLendingService;
    }

    @ApiOperation(value = "Register an account", notes = "Registers an account to library management system.")
    @PostMapping(path = "/register", consumes = "application/json", produces = "application/json")
    public Account register(@Validated @RequestBody final Account account) {
        return accountManagementService.register(account);
    }

    @ApiOperation(value = "Login an account", notes = "Logins an account to library management system.")
    @PostMapping(path = "/login", consumes = "application/json", produces = "application/json")
    public Profile login(@Valid @RequestBody final LoginCredential loginCredential) {
        Account accountInfo = accountManagementService.login(loginCredential);
        List<BookItem> bookIssued = bookLendingService.fetchBooksIssued(loginCredential.getUsername());
        return Profile.builder().accountInfo(accountInfo).booksIssued(bookIssued).build();
    }

}
