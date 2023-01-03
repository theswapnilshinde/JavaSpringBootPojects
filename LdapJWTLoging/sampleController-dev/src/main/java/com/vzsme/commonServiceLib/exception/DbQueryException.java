package com.vzsme.commonServiceLib.exception;

import com.vzsme.commonServiceLib.exception.base.BaseApiException;
import org.springframework.http.HttpStatus;

public class DbQueryException extends BaseApiException {
    public DbQueryException(String message, Throwable t) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "DB Query Error", message, t); //500
    }
}
