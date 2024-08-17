package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
public class AccountService {

    @Autowired
    private final  AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public Account registerAccount(Account account){
        return this.accountRepository.save(account);
    }

    public Account findByAccountId(int accountId){
        return this.accountRepository.findByAccountId(accountId);
    }

    public Account findByUsername(String username){
        return this.accountRepository.findByUsername(username);
    }

    public Account loginUser(Account account){
        Account possibleaccount = this.accountRepository.findByUsername(account.getUsername());
        if (possibleaccount!=null && possibleaccount.getPassword().equals(account.getPassword()))
            return possibleaccount;
        return null;
    }

}
