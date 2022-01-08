package com.demo.account.exception.server;

public class NoRollBackException extends InternalServerException {

    public NoRollBackException(final String message) {
        super(message);
    }
}