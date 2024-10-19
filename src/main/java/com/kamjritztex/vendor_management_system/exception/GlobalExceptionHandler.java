package com.kamjritztex.vendor_management_system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handleCustomException(CustomException ex){
       ApiError apiError=ApiError.builder().message(ex.getMessage())
               .httpStatus(ex.getHttpStatus())
               .createdAt(LocalDateTime.now())
               .build();
       return new ResponseEntity<>(apiError,apiError.getHttpStatus());
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex){
        ApiError apiError=ApiError.builder().message(ex.getMessage()).httpStatus(HttpStatus.INTERNAL_SERVER_ERROR).createdAt(LocalDateTime.now()).build();
        return new ResponseEntity<>(apiError,apiError.getHttpStatus());
    }
}
