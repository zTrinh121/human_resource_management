package com.hr.management.controller;

import com.hr.management.exception.DataNotFoundException;
import com.hr.management.request.DepartmentsRequest;
import com.hr.management.response.DepartmentsResponse;
import com.hr.management.response.ResponseHandler;
import com.hr.management.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("${apiPrefix}/departments")
@RequiredArgsConstructor
public class DepartmentController {


    private final DepartmentService departmentService;
    private static final Logger logInfo = LoggerFactory.getLogger(DepartmentsResponse.class);

    @GetMapping(value = "/{departmentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getDepartmentById(@PathVariable("departmentId") Long id) {
        logInfo.debug("Hello, it is a debug logging");
        logInfo.info("Hello, it is a info logging");

        return ResponseHandler.responseBuilder("Requested department is given here",
                HttpStatus.OK,
                departmentService.getDepartmentById(id));
    }

    @GetMapping(value="/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllDepartments(){
        return ResponseHandler.responseBuilder("Requested departments list is given here",
                HttpStatus.OK,
                departmentService.getAllDepartments());
    }

    @PostMapping("")
    public ResponseEntity<Object> createDepartment(@RequestBody @Valid DepartmentsRequest departmentsRequest
    , BindingResult result) {

        if(result.hasErrors()){
            List<String> errorMessages =  result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();

            return ResponseHandler.responseBuilder("There some errors while inputting data",
                    HttpStatus.BAD_REQUEST,
                    errorMessages);
        }
        return ResponseHandler.responseBuilder("Department has been created successfully",
                HttpStatus.OK,
                departmentService.createDepartment(departmentsRequest));

    }

    @PutMapping("/{departmentId}")
    public ResponseEntity<Object> updateDepartment(@PathVariable("departmentId") Long id,
                                                  @RequestBody DepartmentsRequest departmentsRequest,
                                                   BindingResult result) {
        if(result.hasErrors()){
            List<String> errorMessages =  result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();

            return ResponseHandler.responseBuilder("There some errors while inputting data",
                    HttpStatus.BAD_REQUEST,
                    errorMessages);
        }

        return ResponseHandler.responseBuilder("Department has been updated successfully",
                HttpStatus.OK,
                departmentService.updateDepartment(id, departmentsRequest));

    }

    @DeleteMapping("/{departmentId}")
    public ResponseEntity<String> deleteDepartment(@PathVariable("departmentId") Long id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.ok("Delete successfully department with id " + id);
    }


}
