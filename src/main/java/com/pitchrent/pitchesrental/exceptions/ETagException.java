package com.pitchrent.pitchesrental.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class ETagException extends BaseException{
    public ETagException(String message) {
        super(message);
    }

    public ETagException(String message, Throwable cause) {
        super(message, cause);
    }
}
