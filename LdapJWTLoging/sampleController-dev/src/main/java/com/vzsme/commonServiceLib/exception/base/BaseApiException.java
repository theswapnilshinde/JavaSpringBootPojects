package com.vzsme.commonServiceLib.exception.base;

import org.springframework.http.HttpStatus;

public abstract class BaseApiException extends RuntimeException {
    private HttpStatus status;
    private String type;

    public BaseApiException(HttpStatus status, String message) {
        this(status, null, message, null);
    }
    public BaseApiException(HttpStatus status, String type, String message) { this(status, type, message, null); }
    public BaseApiException(HttpStatus status, String message, Throwable t) { this(status, null, message, t); }
    public BaseApiException(HttpStatus status, String type, String message, Throwable t) {
        super(message, t);
        this.status = status;
        this.type = type;
    }

    protected HttpStatus getHttpStatus() { return this.status; }
    protected String  getType() { return this.type; }
}
