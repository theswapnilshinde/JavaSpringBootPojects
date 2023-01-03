package com.vzsme.commonServiceLib.exception;

import com.vzsme.commonServiceLib.exception.base.BaseApiException;
import org.springframework.http.HttpStatus;

public class ItemNotFoundException extends BaseApiException {
    public ItemNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, "Item Not Found", message); //404
    }
}
