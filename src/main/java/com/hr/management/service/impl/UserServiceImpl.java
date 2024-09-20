package com.hr.management.service.impl;

import com.hr.management.exception.DataNotFoundException;
import com.hr.management.mapper.RolesMapper;
import com.hr.management.mapper.UsersMapper;
import com.hr.management.model.Users;
import com.hr.management.model.UsersExample;
import com.hr.management.model.UsersFull;
import com.hr.management.request.UsersRequest;
import com.hr.management.response.UsersResponse;
import com.hr.management.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    @Autowired
    UsersMapper usersMapper;
    @Autowired
    RolesMapper rolesMapper;

    UsersExample userExample;

    @Override
    public UsersResponse getUserById(Long id) throws Exception {
        UsersFull users = usersMapper.getAllUserDetailByUserId(id);
        if (users == null) {
            throw new DataNotFoundException(String.format("User with ID = %d cannot found", id));
            
        }
        return UsersResponse.fromUserFull(users);
    }

    @Override
    public List<UsersResponse> getAllUsers() {
        List<UsersFull> usersFullList = usersMapper.getAllUserDetail();
        List<UsersResponse> users = usersFullList.stream()
        .map(user -> UsersResponse.fromUserFull(user))
        .toList();
        return users;
    }

    @Override
    public UsersResponse createUser(UsersRequest UsersRequest) {
        return null;
    }

    @Override
    public UsersResponse updateUser(Long id, UsersRequest UsersRequest) throws Exception {
        return null;
    }

    @Override
    public void deleteUser(Long id) throws DataNotFoundException {

    }
}
