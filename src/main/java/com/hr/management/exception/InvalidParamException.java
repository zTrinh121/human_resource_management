package com.hr.management.exception;

public class InvalidParamException extends Exception{
    public InvalidParamException(String message){
        super(message);
    }

    public InvalidParamException(String message, Throwable throwable){
        super(message, throwable);
    }
}
