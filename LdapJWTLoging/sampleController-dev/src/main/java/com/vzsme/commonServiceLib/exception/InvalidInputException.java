package com.vzsme.commonServiceLib.exception;

import com.vzsme.commonServiceLib.exception.base.BaseApiException;
import org.springframework.http.HttpStatus;

public class InvalidInputException extends BaseApiException {
    public InvalidInputException(String message) {
        super(HttpStatus.BAD_REQUEST, "Invalid Input", message); //400
    }
}
