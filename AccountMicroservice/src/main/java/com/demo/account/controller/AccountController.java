package com.demo.account.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.demo.account.bean.request.AccountRequest;
import com.demo.account.entity.Account;
import com.demo.account.service.AccountService;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
@Validated
@Tag(name = "Customer Account APIs", description = "These APIs are used to create and retrieve customer accounts.")
public class AccountController {

    private final AccountService accountService;

    @GetMapping(value = "/{accountNumber}", produces = APPLICATION_JSON_VALUE)
    public Account getAccountDetail(@PathVariable @Min(value = 1000001) final long accountNumber) {
        log.debug("Account retrieval action.");
        return accountService.retrieve(accountNumber);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Account createAccount(@Valid @RequestBody final AccountRequest accountRequest) {
        log.debug("Account creation action.");
        return accountService.createAccount(accountRequest);
    }
    
    @GetMapping("/{accountNumber}/transactions")
    public ResponseEntity<Map<Long, String>> getAllTransactionStatus(@PathVariable long accountNumber){
    	Map<Long, String> tranDetails = accountService.getAllTransactionDetails(accountNumber,accountNumber);
		return new ResponseEntity<Map<Long, String>>(tranDetails,HttpStatus.OK);
    }
}
