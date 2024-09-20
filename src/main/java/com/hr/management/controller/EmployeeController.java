package com.hr.management.controller;

import com.hr.management.exception.DataNotFoundException;
import com.hr.management.model.EmployeeFull;
import com.hr.management.model.Employees;
import com.hr.management.request.EmployeesRequest;
import com.hr.management.response.EmployeesResponse;
import com.hr.management.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<?> getEmployeeById(@PathVariable("id") Long id){
        try{
            EmployeesResponse employee = employeeService.getEmployeeById(id);
            return ResponseEntity.ok(employee);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }


    }

    @GetMapping("/all")
    public ResponseEntity<List<EmployeesResponse>> selectEmployeesWithDetails(){
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PostMapping("/create")
    public ResponseEntity<?> createEmployee(
            @Valid @RequestBody EmployeesRequest employeesRequest,
            BindingResult result){
        try {
            if(result.hasErrors()){
                List<String> errorMessages =  result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }

            EmployeesResponse employeesResponse = employeeService.createEmployee(employeesRequest);
            return ResponseEntity.ok(employeesResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateEmployee(@PathVariable("userId") Long id,
                                            @Valid @RequestBody EmployeesRequest employeesRequest,
                                            BindingResult result){
        try {
            if(result.hasErrors()){
                List<String> errorMessages =  result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }

            EmployeesResponse employeesResponse = employeeService.updateEmployee(id, employeesRequest);
            return ResponseEntity.ok(employeesResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("userId") Long id){
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Deleting successfully employee with ID = " + id);
    }
}
