package com.example.service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

public class AccountService {

    private final  AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public Account registerAccount(Account account){
        boolean notexists=accountRepository.getAccountByUsername(account.getUsername())==null;
        boolean notblank=account.getUsername().length()>0;
        boolean nottooshort=account.getPassword().length()>4;
        if (notexists && notblank && nottooshort) return this.accountRepository.save(account);
        return null;
    }

    public Account login(Account account){
        Account possibleaccount = this.accountRepository.getAccountByUsername(account.getUsername());
        if (possibleaccount!=null && possibleaccount.getPassword().equals(account.getPassword()))
            return possibleaccount;
        return null;
    }
}
