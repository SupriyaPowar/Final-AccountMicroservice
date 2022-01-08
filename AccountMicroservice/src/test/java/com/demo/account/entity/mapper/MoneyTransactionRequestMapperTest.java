package com.demo.account.entity.mapper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.demo.account.bean.request.MoneyTransactionRequest;
import com.demo.account.entity.MoneyTransaction;
import com.demo.account.entity.mapper.MoneyTransactionRequestMapper;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MoneyTransactionRequestMapperTest {

    private final MoneyTransactionRequestMapper moneyTransactionRequestMapper = new MoneyTransactionRequestMapper();

    @Test
    void test_mapFrom_positive_case() {
        //Assemble
        final MoneyTransactionRequest request = MoneyTransactionRequest.builder()
                .fromAccountNumber(1000010)
                .toAccountNumber(1000020)
                .referenceNotes("Fund transfer to friend 2.")
                .transactionAmount(new BigDecimal("1000"))
                .build();
        //Action
        final MoneyTransaction transaction = moneyTransactionRequestMapper.mapFrom(request);

        //Assert
        assertNotNull(transaction);
        assertEquals(request.getFromAccountNumber(), transaction.getFromAccountNumber());
        assertEquals(request.getToAccountNumber(), transaction.getToAccountNumber());
        assertEquals(request.getReferenceNotes(), transaction.getReferenceNotes());
        assertEquals(request.getTransactionAmount(), transaction.getTransactionAmount());
        assertFalse(transaction.getTransactionId() > 0);
    }
}