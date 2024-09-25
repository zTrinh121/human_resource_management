package com.hr.management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DataNotFoundExceptionHandler {

    @ExceptionHandler(value = {DataNotFoundException.class})
    public ResponseEntity<Object> handleDataNotFoundException(
        DataNotFoundException dataNotFoundException) {
        DataException dataException = new DataException(
                dataNotFoundException.getMessage(),
                dataNotFoundException.getCause(),
                HttpStatus.NOT_FOUND
        );
        return new ResponseEntity<>(dataException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {InvalidParamException.class})
    public ResponseEntity<Object> handleInvalidParamException(
            InvalidParamException invalidParamException) {
        DataException dataException = new DataException(
                invalidParamException.getMessage(),
                invalidParamException.getCause(),
                HttpStatus.NOT_FOUND
        );
        return new ResponseEntity<>(dataException, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        e.printStackTrace(); // This will print the full stack trace to the console
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An error occurred: " + e.getMessage());
    }
}
