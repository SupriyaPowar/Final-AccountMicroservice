package com.demo.account.service;

import com.demo.account.bean.request.MoneyTransactionRequest;
import com.demo.account.entity.MoneyTransaction;

public interface MoneyTransactionService {
    MoneyTransaction transferMoney(final MoneyTransactionRequest moneyTxReq);
}
