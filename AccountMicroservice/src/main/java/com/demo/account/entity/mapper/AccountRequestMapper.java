package com.demo.account.entity.mapper;

import org.springframework.stereotype.Component;

import com.demo.account.bean.request.AccountRequest;
import com.demo.account.entity.Account;

@Component
public class AccountRequestMapper implements RequestMapper<AccountRequest, Account> {

    @Override
    public Account mapFrom(AccountRequest request) {
        return Account.builder()
                .holderFirstName(request.getHolderFirstName())
                .holderLastName(request.getHolderLastName())
                .balance(request.getBalance())
                .build();
    }
}
