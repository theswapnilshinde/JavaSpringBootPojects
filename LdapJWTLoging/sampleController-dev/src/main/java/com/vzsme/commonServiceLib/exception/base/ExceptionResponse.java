package com.vzsme.commonServiceLib.exception.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@JsonInclude(JsonInclude.Include.NON_NULL) // Null items are not sent in response
public class ExceptionResponse {
    public Instant  timestamp;
    public Integer status;
    public String error;
    public String type;
    public String message;
    public ExceptionCause cause;

    public ExceptionResponse(HttpStatus code, Exception ex) {
        this.status = code.value();
        this.error = code.getReasonPhrase();
        this.message = ex.getMessage();
        this.type = (ex instanceof BaseApiException ? ((BaseApiException) ex).getType() : null);
        this.timestamp = Instant.now();
        this.cause = (ex.getCause() != null ? new ExceptionCause(ex.getCause()) : null);
    }
}


@JsonInclude(JsonInclude.Include.NON_NULL)
class ExceptionCause {
    public String message;
    public StackTraceElement[] stackTrace;
    public ExceptionCause cause;//Nested exceptions

    ExceptionCause(Throwable cause) {
        this.message = cause.getMessage();
        this.stackTrace = cause.getStackTrace();
        this.cause = (cause.getCause() != null ? new ExceptionCause(cause.getCause()) : null);
    }
}
