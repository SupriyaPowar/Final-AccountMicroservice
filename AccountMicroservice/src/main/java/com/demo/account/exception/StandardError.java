package com.demo.account.exception;

import java.util.List;

import com.demo.account.bean.response.ErrorMessage;

public interface StandardError {
	
    String getErrorCode();
    
    String getMessage();
    
    default ErrorMessage getErrorMessage() {
        return new ErrorMessage(getErrorCode(), List.of(getMessage()));
    }
}
