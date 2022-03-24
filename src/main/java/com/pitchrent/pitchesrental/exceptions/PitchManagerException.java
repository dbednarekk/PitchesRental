package com.pitchrent.pitchesrental.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST)
public class PitchManagerException extends BaseException{
    public PitchManagerException(String message) {
        super(message);
    }

    public PitchManagerException(String message, Throwable cause) {
        super(message, cause);
    }
}
