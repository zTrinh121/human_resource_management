package com.hr.management.controller;

import com.hr.management.exception.DataNotFoundException;
import com.hr.management.request.JobsRequest;
import com.hr.management.request.RolesRequest;
import com.hr.management.response.JobsResponse;
import com.hr.management.response.RolesResponse;
import com.hr.management.service.JobService;
import com.hr.management.service.RoleService;
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
@RequestMapping("${apiPrefix}/roles")
@RequiredArgsConstructor
public class RoleController {

    @Autowired
    RoleService roleService;

    @GetMapping("/{roleId}")
    public ResponseEntity<?> getRoleById(@PathVariable("roleId") Long id) {
        RolesResponse role = roleService.getRoleById(id);
        if(role == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No role found with id = " + id);
        }
        return ResponseEntity.ok(role);
    }

    @GetMapping("/all")
    public ResponseEntity<List<RolesResponse>> getAllRoles(){
        List<RolesResponse> roles = roleService.getAllRoles();
        return ResponseEntity.ok(roles);
    }

    @PostMapping("")
    public ResponseEntity<?> createRole(@RequestBody @Valid RolesRequest rolesRequest,
                                                    BindingResult result){
        if(result.hasErrors()){
            List<String> errorMessages =  result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(errorMessages);
        }
        RolesResponse rolesResponse = roleService.createRole(rolesRequest);
        return ResponseEntity.ok(rolesResponse);
    }

    @PutMapping("/{roleId}")
    public ResponseEntity<?> updateRole(@PathVariable("roleId") Long id,
                                        @Valid @RequestBody RolesRequest rolesRequest,
                                        BindingResult result) {
        try {
            if(result.hasErrors()){
                List<String> errorMessages =  result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }

            return ResponseEntity.ok(roleService.updateRole(id, rolesRequest));
        } catch (Exception e) {
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{roleId}")
    public ResponseEntity<String> deleteRole(@PathVariable("roleId") Long id){
        try {
            roleService.deleteRole(id);
        } catch (DataNotFoundException e) {
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok("Delete successfully role with id = " + id);
    }


}
