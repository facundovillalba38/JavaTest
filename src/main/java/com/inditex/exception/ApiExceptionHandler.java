package com.inditex.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    private HttpStatus badRequest = HttpStatus.BAD_REQUEST;

    public ApiException apiException(String message) {
        return new ApiException(
                message,
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
    }

    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiRequestException e) {

        return new ResponseEntity<>(this.apiException(e.getMessage()), badRequest);
    }
}

