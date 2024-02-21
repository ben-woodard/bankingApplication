package com.coderscampus.assignment13.service;

import com.coderscampus.assignment13.domain.Account;
import com.coderscampus.assignment13.domain.User;
import com.coderscampus.assignment13.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {
    private final UserService userService;
    private final AccountRepository accountRepo;

    @Autowired
    public AccountService(UserService userService, AccountRepository accountRepo) {
        this.userService = userService;
        this.accountRepo = accountRepo;
    }

    public Account findByID(Long accountId) {
        Optional<Account> account = accountRepo.findById(accountId);
        return account.orElse(new Account());
    }

    public User save(User user) {
        return userService.save(user);
    }

    public Account createAccountName(User user, Optional<String> accountName) {
        Account account = new Account();
        String inputAccountName = accountName.orElse("");
        if(inputAccountName == "") {
            account.setAccountName("Account #" + (user.getAccounts().size() + 1));
        } else {
            account.setAccountName(accountName.get());
        }
        return account;
    }

    public Account createAccountByUserIdOptName(Long userId, Optional<String> accountName) {
        User user = userService.findById(userId);
        Account account = createAccountName(user, accountName);
        account.getUsers().add(user);
        user.getAccounts().add(account);
        return accountRepo.save(account);
    }

    public Account saveAccountByIdAndName(Long accountId, String accountName) {
        Account account = accountRepo.findById(accountId).orElse(new Account());
        if (accountName == "") {
            account.setAccountName(account.getAccountName());
        } else {
            account.setAccountName(accountName);
        }
        return accountRepo.save(account);
    }
}
