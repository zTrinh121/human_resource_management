package com.hr.management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HrManagementExceptionHandler {

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

    @ExceptionHandler(value = {JobHasAssociatedEmployeeException.class})
    public ResponseEntity<Object> handleJobHasAssociatedEmployeeException(
            JobHasAssociatedEmployeeException jobHasAssociatedEmployeeException) {
        DataException dataException = new DataException(
                jobHasAssociatedEmployeeException.getMessage(),
                jobHasAssociatedEmployeeException.getCause(),
                HttpStatus.CONFLICT
        );
        return new ResponseEntity<>(dataException, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(value = {DepartmentHasAssociatedEmployeeException.class})
    public ResponseEntity<Object> handleJobHasAssociatedEmployeeException(
            DepartmentHasAssociatedEmployeeException departmentHasAssociatedEmployeeException) {
        DataException dataException = new DataException(
                departmentHasAssociatedEmployeeException.getMessage(),
                departmentHasAssociatedEmployeeException.getCause(),
                HttpStatus.CONFLICT
        );
        return new ResponseEntity<>(dataException, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {MappingException.class})
    public ResponseEntity<Object> handleMappingException(
            MappingException mappingException) {
        DataException dataException = new DataException(
                mappingException.getMessage(),
                mappingException.getCause(),
                HttpStatus.CONFLICT
        );
        return new ResponseEntity<>(dataException, HttpStatus.CONFLICT);
    }
    

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An error occurred: " + e.getMessage());
    }


}
