package com.vzsme.commonServiceLib.exception;

import com.vzsme.commonServiceLib.exception.base.BaseApiException;
import org.springframework.http.HttpStatus;

public class ItemExistsException extends BaseApiException {
    public ItemExistsException(String message) {
        super(HttpStatus.CONFLICT, "Item Exists", message); //409
    }
}
