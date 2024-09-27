package com.hr.management.exception;

public class MappingException extends Exception{
    public MappingException(String message){
        super(message);
    }

    public MappingException(String message, Throwable throwable){
        super(message, throwable);
    }
}
