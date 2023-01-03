package com.vzsme.commonServiceLib.exception;

import com.vzsme.commonServiceLib.exception.base.BaseApiException;
import org.springframework.http.HttpStatus;

public class ApiException extends BaseApiException {
    public ApiException(int httpCode, String message) {
        super(HttpStatus.valueOf(httpCode), "API Error", message);
    }
}
