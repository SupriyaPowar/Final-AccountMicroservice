package com.demo.account;

import org.junit.jupiter.api.Test;

import com.demo.account.AccountServiceApplication;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class AccountServiceIntegrationTest {

    @Test
    void test_main() {
        AccountServiceApplication.main(new String[] {"--spring.profiles.active=test"});
        assertTrue(true);
    }
}
