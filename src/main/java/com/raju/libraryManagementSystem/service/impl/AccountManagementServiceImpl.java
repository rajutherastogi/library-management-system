package com.raju.libraryManagementSystem.service.impl;

import com.raju.libraryManagementSystem.constants.ErrorCodes;
import com.raju.libraryManagementSystem.entity.Account;
import com.raju.libraryManagementSystem.entity.LoginCredential;
import com.raju.libraryManagementSystem.exception.UserErrorException;
import com.raju.libraryManagementSystem.repository.AccountRepository;
import com.raju.libraryManagementSystem.service.AccountManagementService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class AccountManagementServiceImpl implements AccountManagementService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountManagementServiceImpl(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account register(final Account account) {
        if (fetchAccount(account.getUserName()).isPresent()) {
            throw new UserErrorException(HttpStatus.CONFLICT, ErrorCodes.ACCOUNT_ALREADY_EXISTS.name(), ErrorCodes.ACCOUNT_ALREADY_EXISTS.getMessage());
        }
        return accountRepository.createAccount(account);
    }

    @Override
    public Set<Account> fetchAccounts() {
        return accountRepository.fetchAllAccounts();
    }

    @Override
    public Optional<Account> fetchAccount(final String userName) {
        return accountRepository.fetchAccount(userName);
    }

    @Override
    public Account login(final LoginCredential loginCredential) {
        String username = loginCredential.getUsername();
        String password = loginCredential.getPassword();
        Optional<Account> optionalAccount = fetchAccount(username);
        if (!optionalAccount.isPresent()) {
            throw new UserErrorException(HttpStatus.NOT_FOUND, ErrorCodes.ACCOUNT_NOT_FOUND.name(), ErrorCodes.ACCOUNT_NOT_FOUND.getMessage());
        }
        Account account = optionalAccount.get();
        if (!StringUtils.equals(account.getPassword(), password)) {
            throw new UserErrorException(ErrorCodes.INVALID_CREDENTIALS.name(), ErrorCodes.INVALID_CREDENTIALS.getMessage());
        }
        return account;
    }
}
