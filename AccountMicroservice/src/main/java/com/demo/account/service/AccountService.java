package com.demo.account.service;

import java.math.BigDecimal;
import java.util.Map;

import com.demo.account.bean.request.AccountRequest;
import com.demo.account.entity.Account;

public interface AccountService {
    Account retrieve(final long accountNumber);
    
    Account createAccount(AccountRequest account);
    
    void makeFundTransfer(final long fromAccountNumber, final long toAccountNumber, final BigDecimal fund);

	Map<Long, String> getAllTransactionDetails(Long fromAccountNumber , Long toAccountNumber);
}
