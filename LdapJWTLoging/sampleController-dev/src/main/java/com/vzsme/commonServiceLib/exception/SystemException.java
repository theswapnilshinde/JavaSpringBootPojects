package com.vzsme.commonServiceLib.exception;

import com.vzsme.commonServiceLib.exception.base.BaseApiException;
import org.springframework.http.HttpStatus;

public class SystemException extends BaseApiException {
    public SystemException(String message) {
        this(message, null);
    }
    public SystemException(String message, Throwable t) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "System Error", message, t); //500
    }
}
