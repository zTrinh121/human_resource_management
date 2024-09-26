package com.hr.management.exception;

public class JobHasAssociatedEmployeeException extends Exception{
    public JobHasAssociatedEmployeeException(String message){
        super(message);
    }

    public JobHasAssociatedEmployeeException(String message, Throwable throwable){
        super(message, throwable);
    }
}
