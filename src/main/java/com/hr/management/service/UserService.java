package com.hr.management.service;

import com.hr.management.exception.DataNotFoundException;
import com.hr.management.request.UsersRequest;
import com.hr.management.response.UsersResponse;

import java.util.List;

public interface UserService {

    UsersResponse getUserById(Long id) throws Exception;

    List<UsersResponse> getAllUsers();

    UsersResponse createUser(UsersRequest UsersRequest);

    UsersResponse updateUser(Long id, UsersRequest UsersRequest) throws Exception;

    void deleteUser(Long id) throws DataNotFoundException;

}
