package com.raju.libraryManagementSystem.repository;

import com.raju.libraryManagementSystem.entity.Account;

import java.util.Optional;
import java.util.Set;


public interface AccountRepository {
    Account createAccount(final Account account);

    Set<Account> fetchAllAccounts();

    Optional<Account> fetchAccount(final String userName);
}
