package com.hr.management.controller;
import com.hr.management.request.RolesRequest;
import com.hr.management.response.ResponseHandler;
import com.hr.management.response.RolesResponse;
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
    public ResponseEntity<Object> getRoleById(@PathVariable("roleId") Long id) {
        return ResponseHandler.responseBuilder("Requested role is given here",
                HttpStatus.OK,
                roleService.getRoleById(id));
    }

@RequestMapping(value = "/all", method = RequestMethod.GET, produces = "application/json")
public ResponseEntity<Object> getAllRoles(){
    try {
        System.out.println("Entering getAllRoles method");
        List<RolesResponse> roles = roleService.getAllRoles();

        roles.forEach(role -> {
            System.out.println("Role ID: " + role.getRoleId());
            System.out.println("Role Name: " + role.getRoleName());
        });
        ResponseEntity<Object> response = ResponseHandler.responseBuilder(
            "Requested roles list is given here",
            HttpStatus.OK,
            roles
        );
        System.out.println("Response created: " + response);
        return response;
    } catch (Exception e) {
        System.err.println("Exception in getAllRoles: " + e.getMessage());
        e.printStackTrace();
        throw e; // Re-throw the exception to be caught by the global handler
    }
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
        return ResponseHandler.responseBuilder("Role has been created successfully",
                HttpStatus.OK,
                roleService.createRole(rolesRequest));
    }

    @PutMapping("/{roleId}")
    public ResponseEntity<?> updateRole(@PathVariable("roleId") Long id,
                                        @Valid @RequestBody RolesRequest rolesRequest,
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

        return ResponseHandler.responseBuilder("Role has been updated successfully",
                HttpStatus.OK,
                roleService.updateRole(id, rolesRequest));
    }

    @DeleteMapping("/{roleId}")
    public ResponseEntity<String> deleteRole(@PathVariable("roleId") Long id) {
        roleService.deleteRole(id);
        return ResponseEntity.ok("Delete successfully role with id = " + id);

    }

}
