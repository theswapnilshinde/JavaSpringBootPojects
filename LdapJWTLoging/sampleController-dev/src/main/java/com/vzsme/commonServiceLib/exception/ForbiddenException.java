package com.vzsme.commonServiceLib.exception;

import com.vzsme.commonServiceLib.exception.base.BaseApiException;
import org.springframework.http.HttpStatus;

public class ForbiddenException extends BaseApiException {
    public ForbiddenException(String message) {
        super(HttpStatus.FORBIDDEN, "User Not Authorized", message); //403
    }
}
