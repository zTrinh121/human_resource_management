package com.hr.management.service.impl;

import com.hr.management.exception.DataNotFoundException;
import com.hr.management.mapper.RolesMapper;
import com.hr.management.mapper.UsersMapper;
import com.hr.management.model.Roles;
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
    public UsersResponse createUser(UsersRequest usersRequest) throws Exception {
        //Check role existing or not
        Roles existingRole = rolesMapper.selectByPrimaryKey(usersRequest.getRoleId());
        if(existingRole == null){
            throw new DataNotFoundException(String.format("Role not found with ID = %d",
                    usersRequest.getRoleId()));
        }

        Users existingUser = usersMapper.selectByUserName(usersRequest.getUserName());
        if(existingUser != null){
            throw new DataNotFoundException(String.format("User name has existed"));
        }

        Users user = Users.fromUserRequest(usersRequest);
        usersMapper.insert(user);
        return getUserById(user.getUserId());

    }

    @Override
    public UsersResponse updateUser(Long id, UsersRequest usersRequest) throws Exception {
        //Check role existing or not
        Roles existingRole = rolesMapper.selectByPrimaryKey(usersRequest.getRoleId());
        if(existingRole == null){
            throw new DataNotFoundException(String.format("Role not found with ID = %d",
                    usersRequest.getRoleId()));
        }

        Users existingUser = usersMapper.selectByPrimaryKey(id);
        if(existingUser == null){
            throw new DataNotFoundException(String.format("User not found with ID = %d",
                    id));
        }

        Users existingUserName = usersMapper.selectByUserName(usersRequest.getUserName());
        if(existingUserName != null &&
        existingUserName.getUserName().equals(usersRequest.getUserName()) &&
        existingUserName.getUserId() != existingUser.getUserId()){
            throw new DataNotFoundException(String.format("User name has existed"));
        }
        existingUser.setUserName(usersRequest.getUserName());
        existingUser.setPassword(usersRequest.getPassword());
        existingUser.setRoleId(usersRequest.getRoleId());
        usersMapper.updateByPrimaryKeySelective(existingUser);

        return getUserById(existingUser.getUserId());
    }

    @Override
    public void deleteUser(Long id) throws DataNotFoundException {
        Users existingUser = usersMapper.selectByPrimaryKey(id);
        if(existingUser == null){
            throw new DataNotFoundException(String.format("User not found with ID = %d",
                    id));
        }

        usersMapper.deleteByPrimaryKey(id);

    }
}
