package com.raju.libraryManagementSystem.repository.impl;

import com.raju.libraryManagementSystem.entity.Account;
import com.raju.libraryManagementSystem.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class AccountRepositoryImpl implements AccountRepository {
    private final Map<String, Account> accountMap;

    @Autowired
    public AccountRepositoryImpl() {
        this.accountMap = new HashMap<>();
    }

    @Override
    public Account createAccount(final Account account) {
        return accountMap.put(account.getUserName(), account);

    }

    @Override
    public Set<Account> fetchAllAccounts() {
        return accountMap.values().stream().collect(Collectors.toSet());
    }

    @Override
    public Optional<Account> fetchAccount(final String userName) {
        return Optional.ofNullable(accountMap.get(userName));
    }
}
