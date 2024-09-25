package com.hr.management.controller;

import com.hr.management.request.EmployeesRequest;
import com.hr.management.response.ResponseHandler;
import com.hr.management.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${apiPrefix}/employees")
@RequiredArgsConstructor
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;


    @GetMapping("/{id}")
    public ResponseEntity<Object> getEmployeeById(@PathVariable("id") Long id){
        return ResponseHandler.responseBuilder("Requested employee is given here",
                HttpStatus.OK,
                employeeService.getEmployeeById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<Object> selectEmployeesWithDetails(){
        return ResponseHandler.responseBuilder("Requested employees list is given here",
                HttpStatus.OK,
                employeeService.getAllEmployees());
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createEmployee(
            @Valid @RequestBody EmployeesRequest employeesRequest,
            BindingResult result){
            if(result.hasErrors()){
                List<String> errorMessages =  result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();

                return ResponseHandler.responseBuilder("There some errors while inputting data",
                        HttpStatus.BAD_REQUEST,
                        errorMessages);
            }
            return ResponseHandler.responseBuilder("Employee has been created",
                    HttpStatus.OK,
                    employeeService.createEmployee(employeesRequest));

    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<?> updateEmployee(@PathVariable("employeeId") Long id,
                                            @Valid @RequestBody EmployeesRequest employeesRequest,
                                            BindingResult result){
        if(result.hasErrors()){
            List<String> errorMessages =  result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();

            return ResponseHandler.responseBuilder("There some errors while inputting data",
                    HttpStatus.BAD_REQUEST,
                    errorMessages);
        }

        return ResponseHandler.responseBuilder("Employee has been updated successfully",
                    HttpStatus.OK,
                    employeeService.updateEmployee(id, employeesRequest));

    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("employeeId") Long id)  {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Deleting successfully employee with ID = " + id);
    }
}
