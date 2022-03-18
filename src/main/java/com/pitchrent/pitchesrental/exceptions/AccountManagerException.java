package com.pitchrent.pitchesrental.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class AccountManagerException extends BaseException{
    public AccountManagerException(String message) {
        super(message);
    }

    public AccountManagerException(String message, Throwable cause) {
        super(message, cause);
    }
}
