package org.root.exceptions;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


//If a client can reasonably be expected to recover from an exception, make it a checked(compile) exception.inherit from the Exception class
//If a client cannot do anything to recover from the exception, make it an unchecked(Runtime) exception.inherit from the RuntimeException class
public enum ResponseCode {
    OK("200", "Success"),

    // Custom Exception
    CUSTOM_EXCEPTION("800", "An error occurred. Please check and try again."),
    INVALID_REQUEST("801", "Invalid request."),
    FORBIDDEN_REQUEST("802", "Request not allowed."),
    DUPLICATED_REQUEST("803", "Duplicate request."),
    NOT_FOUND("804", "Information not found."),

    // Exception Handler
    INTERNAL_SERVER_ERROR("900", "An internal error occurred. Please check and try again."),
    METHOD_ARGUMENT_NOT_VALID("901", "Invalid parameter."),
    MISSING_SERVLET_REQUEST_PARAMETER("902", "Required parameter is missing."),
    CONSTRAINT_VIOLATION("903", "Parameter validation failed."),
    METHOD_ARGUMENT_TYPE_MISMATCH("904", "Incorrect parameter type."),
    HTTP_MESSAGE_NOT_READABLE_EXCEPTION("905", "Request information cannot be read."),
    NO_HANDLER_FOUND("906", "Requested URL not found."),
    HTTP_REQUEST_METHOD_NOT_SUPPORTED("907", "Unsupported method."),
    HTTP_MEDIA_TYPE_NOT_SUPPORTED("908", "Unsupported media type.");

    private final String code;
    private final String message;

    ResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private static final Map<String, ResponseCode> BY_STATUS_CODE =
            Stream.of(values())
                    .collect(Collectors.toMap(ResponseCode::getCode, Function.identity()));

    private static final Map<String, ResponseCode> BY_MESSAGE =
            Stream.of(values())
                    .collect(Collectors.toMap(ResponseCode::getMessage, Function.identity()));

    public static ResponseCode valueOfStatusCode(String statusCode) {
        return BY_STATUS_CODE.get(statusCode);
    }

    public static ResponseCode valueOfMessage(String message) {
        return BY_MESSAGE.get(message);
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
