package com.hr.management.exception;

public class DataNotFoundException extends RuntimeException {
    public DataNotFoundException(String message){
        super(message);
    }

    public DataNotFoundException(String message, Throwable throwable){
        super(message, throwable);
    }
}