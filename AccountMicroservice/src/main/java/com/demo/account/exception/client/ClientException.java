package com.demo.account.exception.client;

import com.demo.account.exception.StandardError;

public class ClientException extends RuntimeException implements StandardError {

    public ClientException(String message) {
        super(message);
    }

    @Override
    public String getErrorCode() {
        return "Something went wrong...";
    }
}
