package com.demo.account;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.demo.account.controller.AccountController;
import com.demo.account.controller.MoneyTransactionController;
import com.demo.account.repository.AccountRepository;
import com.demo.account.repository.MoneyTransactionRepository;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class AccountServiceApplicationTests {

	@Autowired
	private AccountController accountController;
	@Autowired
	private MoneyTransactionController moneyTransactionController;
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private MoneyTransactionRepository moneyTransactionRepository;

	@Test
	void contextLoads() throws Exception {
		Assertions.assertNotNull(accountController);
		Assertions.assertNotNull(moneyTransactionController);
		Assertions.assertNotNull(accountRepository);
		Assertions.assertNotNull(moneyTransactionRepository);
	}

}
