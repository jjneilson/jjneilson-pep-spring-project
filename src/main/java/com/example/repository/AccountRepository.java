package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entity.Account;

public interface AccountRepository extends JpaRepository<Account,Long> {

    @Query("FROM account WHERE username = :username")
    public Account getAccountByUsername(@Param("username") String username);

    @Query("FROM account")
    public List<Account> getAllAccounts();
}
