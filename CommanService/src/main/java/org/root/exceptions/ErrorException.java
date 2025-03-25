package org.root.exceptions;

import lombok.Getter;

public class ErrorException extends RuntimeException {
    @Getter
    private ResponseCode responseCode;
    private String message;

    public ErrorException(ResponseCode rtaStatusCode) {
        super();
        this.responseCode = rtaStatusCode;
        this.message = rtaStatusCode.getMessage();
    }

    public ErrorException(String message) {
        super();
        this.responseCode = ResponseCode.CUSTOM_EXCEPTION;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
