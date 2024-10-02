package com.hr.management.controller;

import com.hr.management.exception.MappingException;
import com.hr.management.request.UsersLoginRequest;
import com.hr.management.request.UsersRequest;
import com.hr.management.response.ResponseHandler;
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


@RestController
@RequestMapping("${apiPrefix}/users")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("all")
    public ResponseEntity<Object> getAllUser() {
        return ResponseHandler.responseBuilder("Requested users list is given here",
                HttpStatus.OK,
                userService.getAllUsers());
    }

    @GetMapping("{userId}")
    public ResponseEntity<Object> getUserById(@PathVariable("userId") Long userId) {
            return ResponseHandler.responseBuilder("Requested user is given here",
                    HttpStatus.OK,
                    userService.getUserById(userId));
    }

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@Valid @RequestBody UsersRequest user,
                                        BindingResult result) throws MappingException {
            if(result.hasErrors()){
                List<String> errorMessages =  result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();

                return ResponseHandler.responseBuilder("There some errors while inputting data",
                        HttpStatus.BAD_REQUEST,
                        errorMessages);
            }
            return ResponseHandler.responseBuilder("User has been created successfully",
                    HttpStatus.OK,
                    userService.createUser(user));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable("userId") Long id,
                                        @Valid @RequestBody UsersRequest user,
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

        return ResponseHandler.responseBuilder("User has been updated successfully",
                HttpStatus.OK,
                userService.updateUser(id, user));

    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable("userId") Long id){
            userService.deleteUser(id);
            return ResponseEntity.ok().body(String.format("Delete successfully user with ID = %d", id));

    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(
            @Valid @RequestBody UsersLoginRequest usersLoginRequest,
            BindingResult result
    ) {
        if(result.hasErrors()){
                List<String> errorMessages =  result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseHandler.responseBuilder("There some errors while inputting data",
                        HttpStatus.BAD_REQUEST,
                        errorMessages);
        }

        String token = null;
        try {
            token = userService.login(usersLoginRequest.getUserName(), usersLoginRequest.getPassword());
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }


    }

}
