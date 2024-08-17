package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {

    public Account findByUsername(String username);

    public Account findByAccountId(int accountId);

    /* 
    @Query("FROM account WHERE username = :username")
    public Account getAccountByUsername(@Param("username") String username);

    @Query("FROM account")
    public List<Account> getAllAccounts();
    */
}
