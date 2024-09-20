package com.hr.management.controller;

import com.hr.management.exception.DataNotFoundException;
import com.hr.management.request.RolesRequest;
import com.hr.management.response.RolesResponse;
import com.hr.management.response.UsersResponse;
import com.hr.management.service.RoleService;
import com.hr.management.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("${apiPrefix}/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("all")
    public ResponseEntity<List<UsersResponse>> getAllUser() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("{userId}")
    public ResponseEntity<?> getUserById(@PathVariable("userId") Long userId) {
        try {
            UsersResponse usersResponse = userService.getUserById(userId);
            return ResponseEntity.ok(usersResponse);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    
    
    

   

}
