package com.hr.management.exception;

public class DepartmentHasAssociatedEmployeeException extends Exception{
    public DepartmentHasAssociatedEmployeeException(String message){
        super(message);
    }

    public DepartmentHasAssociatedEmployeeException(String message, Throwable throwable){
        super(message, throwable);
    }
}
