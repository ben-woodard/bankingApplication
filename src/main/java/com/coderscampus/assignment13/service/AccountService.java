package com.coderscampus.assignment13.service;

import com.coderscampus.assignment13.domain.Account;
import com.coderscampus.assignment13.domain.User;
import com.coderscampus.assignment13.repository.AccountRepository;
import com.coderscampus.assignment13.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Account saveAccountandUser(Account account, User user) {
        user.getAccounts().add(account);
        account.getUsers().add(user);
        userRepo.save(user);
        return accountRepo.save(account);
    }

    public Account createAccount(User user) {
        Account account = new Account();
        account.setAccountName("Account #" + (user.getAccounts().size() + 1));
        user.getAccounts().add(account);
        account.getUsers().add(user);
        return accountRepo.save(account);
    }




}
