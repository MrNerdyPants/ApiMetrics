package com.dust.monitoring.api.analytics.enums;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    INVALID_INPUT("E001", "Invalid input provided", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND("E002", "User not found", HttpStatus.NOT_FOUND),
    SERVER_ERROR("E003", "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String code;

    private final String message;

    private final HttpStatus httpStatus;

    ErrorCode(String code, String message, HttpStatus httpStatus) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
