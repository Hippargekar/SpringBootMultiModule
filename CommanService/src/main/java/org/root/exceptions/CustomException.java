package org.root.exceptions;

import lombok.Getter;

public class CustomException extends Exception{
    @Getter
    private ResponseCode responseCode;
    private String message;

    public CustomException(ResponseCode rtaStatusCode) {
        super();
        this.responseCode = rtaStatusCode;
        this.message = rtaStatusCode.getMessage();
    }

    public CustomException(String message) {
        super();
        this.responseCode = ResponseCode.CUSTOM_EXCEPTION;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
