package com.kamjritztex.vendor_management_system.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
public class CustomException extends RuntimeException {
    private String message;
    private HttpStatus httpStatus;

    public void setMessage(String message) {
        this.message = message;
    }

    public CustomException(String message) {
        this.message = message;
    }

    public CustomException(String message, HttpStatus httpStatus) {
        this.message=message;
        this.httpStatus = httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }


}
