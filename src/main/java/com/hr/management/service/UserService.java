package com.hr.management.service;

import com.hr.management.exception.DataNotFoundException;
import com.hr.management.exception.MappingException;
import com.hr.management.request.UsersRequest;
import com.hr.management.response.LoginResponse;
import com.hr.management.response.UsersResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {

    UsersResponse getUserById(Long id);

    List<UsersResponse> getAllUsers();

    UsersResponse createUser(UsersRequest UsersRequest) throws MappingException;

    UsersResponse updateUser(Long id, UsersRequest UsersRequest) ;

    void deleteUser(Long id) throws DataNotFoundException;

    LoginResponse login(String username, String password) throws Exception;

    UsersResponse uploadImage(Long id, MultipartFile file);

}
