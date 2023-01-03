package com.vzsme.commonServiceLib.exception;

import com.vzsme.commonServiceLib.exception.base.BaseApiException;
import org.springframework.http.HttpStatus;

//@ResponseStatus(value = HttpStatus.UNAUTHORIZED) //401
public class UnauthorizedException extends BaseApiException {
    public UnauthorizedException(String message) {
        super(HttpStatus.UNAUTHORIZED, "User Not Authenticated", message); //401
    }
}
