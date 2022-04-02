package com.pitchrent.pitchesrental.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class RentalManagerException extends BaseException{
    public RentalManagerException(String message) {
        super(message);
    }

    public RentalManagerException(String message, Throwable cause) {
        super(message, cause);
    }
}
