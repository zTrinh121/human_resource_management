package com.hr.management.controller;

import com.hr.management.model.EmployeeFull;
import com.hr.management.model.Employees;
import com.hr.management.response.EmployeesResponse;
import com.hr.management.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${apiPrefix}/employees")
@RequiredArgsConstructor
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable("id") Long id){
        EmployeesResponse employee = employeeService.getEmployeeById(id);
        if(employee == null){
            return ResponseEntity.badRequest().body("No employee found");
        }
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/all")
    public ResponseEntity<List<EmployeesResponse>> selectEmployeesWithDetails(){
        return ResponseEntity.ok(employeeService.selectEmployeesWithDetails());
    }
}
