package com.kamjritztex.vendor_management_system.exception;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
public class ApiError {
    private HttpStatus httpStatus;
    private LocalDateTime createdAt;
    private String message;


}
