package com.coderscampus.assignment13.service;

import com.coderscampus.assignment13.domain.Account;
import com.coderscampus.assignment13.domain.User;
import com.coderscampus.assignment13.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepo;

    public Account findByID(Long accountId) {
      Optional<Account> account = accountRepo.findById(accountId);
      return account.orElse(new Account());
    }

    public Account saveAccount(String accountName, User user) {
        Account account = new Account();
        account.setAccountName(accountName);
        account.getUsers().add(user);
        user.getAccounts().add(account);
        accountRepo.save(account);
        return account;
    }
}
