package com.demo.account.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.demo.account.bean.request.MoneyTransactionRequest;
import com.demo.account.entity.MoneyTransaction;
import com.demo.account.service.MoneyTransactionService;

import javax.validation.Valid;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/transactions")
@Tag(name = "Money Transfer API", description = "This API is used to send money from one account to another account.")
public class MoneyTransactionController {

    private final MoneyTransactionService moneyTransactionService;

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public MoneyTransaction transferMoney(@Valid @RequestBody final MoneyTransactionRequest moneyTransactionRequest) {
        log.debug("Money transaction action.");
        return moneyTransactionService.transferMoney(moneyTransactionRequest);
    }

}
