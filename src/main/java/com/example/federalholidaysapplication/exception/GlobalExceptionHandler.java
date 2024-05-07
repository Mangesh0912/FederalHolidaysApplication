package com.example.federalholidaysapplication.exception;

import com.example.federalholidaysapplication.model.ApiResponseObject;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ApiResponseObject<Object>> handleServiceException(ServiceException ex) {
        ApiResponseObject<Object> response = new ApiResponseObject<>(null, ex.getMessage(), null);
        HttpStatusCode httpStatusCode = ex.getMessage().equals("Holiday not found") ? HttpStatus.NOT_FOUND : HttpStatus.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(response, httpStatusCode);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseObject<Object>> handleException(Exception ex) {
        ApiResponseObject<Object> response = new ApiResponseObject<>(null, "Internal server error: " + ex.getMessage(), null);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
