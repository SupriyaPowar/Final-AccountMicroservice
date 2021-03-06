package com.demo.account.exception.server;

import com.demo.account.exception.StandardError;

public class InternalServerException extends RuntimeException implements StandardError {

    public InternalServerException(final String message) {
        super(message);
    }

    @Override
    public String getErrorCode() {
        return "SERVER_ERROR";
    }
}