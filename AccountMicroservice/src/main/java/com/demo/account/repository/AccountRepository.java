package com.demo.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.account.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByAccountNumber(final long accountNumber);
    
    
}
