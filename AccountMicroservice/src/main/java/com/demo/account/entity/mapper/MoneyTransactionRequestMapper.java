package com.demo.account.entity.mapper;

import org.springframework.stereotype.Component;

import com.demo.account.bean.request.MoneyTransactionRequest;
import com.demo.account.entity.MoneyTransaction;

@Component
public class MoneyTransactionRequestMapper implements RequestMapper<MoneyTransactionRequest, MoneyTransaction> {

    @Override
    public MoneyTransaction mapFrom(MoneyTransactionRequest request) {
        return MoneyTransaction.builder()
                .fromAccountNumber(request.getFromAccountNumber())
                .toAccountNumber(request.getToAccountNumber())
                .transactionAmount(request.getTransactionAmount())
                .referenceNotes(request.getReferenceNotes())
                .build();
    }
}
