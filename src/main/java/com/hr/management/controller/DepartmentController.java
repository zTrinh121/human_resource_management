package com.hr.management.controller;

import com.hr.management.exception.DataNotFoundException;
import com.hr.management.request.DepartmentsRequest;
import com.hr.management.response.DepartmentsResponse;
import com.hr.management.service.DepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${apiPrefix}/departments")
@RequiredArgsConstructor
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @GetMapping("/{departmentId}")
    public ResponseEntity<?> getDepartmentById(@PathVariable("departmentId") Long id) {
        DepartmentsResponse department = departmentService.getDepartmentById(id);
        if(department == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No department found with id = " + id);
        }
        return ResponseEntity.ok(department);
    }

    @GetMapping("/all")
    public ResponseEntity<List<DepartmentsResponse>> getAllDepartments(){
        List<DepartmentsResponse> departments = departmentService.getAllDepartments();
        return ResponseEntity.ok(departments);
    }

    @PostMapping("")
    public ResponseEntity<?> createDepartment(@RequestBody @Valid DepartmentsRequest departmentsRequest) {
        try {
            DepartmentsResponse departmentsResponse = departmentService.createDepartment(departmentsRequest);
            return ResponseEntity.ok(departmentsResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{departmentId}")
    public ResponseEntity<?> updateDepartment(@PathVariable("departmentId") Long id,
                                                  @RequestBody DepartmentsRequest departmentsRequest) {

        try {
            return ResponseEntity.ok(departmentService.updateDepartment(id, departmentsRequest));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{departmentId}")
    public ResponseEntity<String> deleteDepartment(@PathVariable("departmentId") Long id) throws Exception {

        departmentService.deleteDepartment(id);
        return ResponseEntity.ok("Delete successfully department with id " + id);
    }


}
