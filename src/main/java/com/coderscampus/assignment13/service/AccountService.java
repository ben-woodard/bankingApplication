package com.coderscampus.assignment13.service;

import com.coderscampus.assignment13.domain.Account;
import com.coderscampus.assignment13.domain.User;
import com.coderscampus.assignment13.repository.AccountRepository;
import com.coderscampus.assignment13.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private UserService userService;
    @Autowired
    private AccountRepository accountRepo;
    @Autowired
    private UserRepository userRepo;

    public Account findByID(Long accountId) {
        Optional<Account> account = accountRepo.findById(accountId);
        return account.orElse(new Account());
    }

    public User save(User user) {
        return userRepo.save(user);
    }


    public Account createAccountByUserId(Long userId, Optional<String> accountName) {
        User user = userService.findById(userId);
        Account account = new Account();
        String inputAccountName = accountName.orElse("");
        if(inputAccountName == ""){
            account.setAccountName("Account #" + (user.getAccounts().size() + 1));
        } else {
            account.setAccountName(inputAccountName);
        }
        account.getUsers().add(user);
        user.getAccounts().add(account);
        return accountRepo.save(account);
    }


    public Account saveAccountByIdAndName(Long accountId, String accountName, Long userId) {
        Account account = accountRepo.findById(accountId).orElse(new Account());
        if (accountName == "") {
            Account storedAccount = accountRepo.findById(accountId).orElse(createAccountByUserId(userId, Optional.of(accountName)));
            account.setAccountName(storedAccount.getAccountName());
        } else {
            account.setAccountName(accountName);
        }
        return accountRepo.save(account);
    }
}
