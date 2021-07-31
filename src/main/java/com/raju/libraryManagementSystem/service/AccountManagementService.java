package com.raju.libraryManagementSystem.service;

import com.raju.libraryManagementSystem.entity.Account;
import com.raju.libraryManagementSystem.entity.LoginCredential;
import com.raju.libraryManagementSystem.entity.Profile;

import java.util.Optional;
import java.util.Set;

public interface AccountManagementService {
    Account register(final Account account);

    Set<Account> fetchAccounts();

    Optional<Account> fetchAccount(final String userName);

    Account login(final LoginCredential loginCredential);
}
