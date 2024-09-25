package com.hr.management.serviceImpl;

import com.hr.management.exception.DataNotFoundException;
import com.hr.management.mapper.RolesMapper;
import com.hr.management.mapper.UsersMapper;
import com.hr.management.model.Roles;
import com.hr.management.model.Users;
import com.hr.management.model.UsersFull;
import com.hr.management.request.UsersRequest;
import com.hr.management.response.UsersResponse;
import com.hr.management.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UsersMapper usersMapper;

    @Mock
    private RolesMapper rolesMapper;

    @InjectMocks
    private UserServiceImpl userService;

    private Users user;
    private Roles role;
    private UsersFull usersFull;
    private UsersRequest usersRequest;

    @BeforeEach
    void setUp() {
        user = new Users(1L, "John", "john123456", 1L);
        role = new Roles(1L, "Admin");
        usersRequest = new UsersRequest("John", "john123456", 1L);
        usersFull = new UsersFull(1L, "John", "john123456", 1L, "Admin");
    }

    @Test
    void testGetUserById_returnUserResponse() throws Exception {
        Mockito.when(usersMapper.getAllUserDetailByUserId(user.getUserId()))
                .thenReturn(usersFull);

        UsersResponse usersResponse = userService.getUserById(user.getUserId());

        Assertions.assertNotNull(usersResponse);
        Assertions.assertEquals(user.getUserName(), usersResponse.getUserName());
    }

    @Test
    void testGetUserById_throwsDataNotFoundException() {
        Mockito.when(usersMapper.getAllUserDetailByUserId(1L)).thenReturn(null);

        Assertions.assertThrows(DataNotFoundException.class, () -> userService.getUserById(1L));
    }

    @Test
    void testCreateUser_returnUserResponse() throws Exception {
        Mockito.when(rolesMapper.selectByPrimaryKey(role.getRoleId())).thenReturn(role);
        Mockito.when(usersMapper.selectByUserName(usersRequest.getUserName())).thenReturn(null);
        Mockito.when(usersMapper.getAllUserDetailByUserId(user.getUserId())).thenReturn(usersFull);
        doAnswer(invocation -> {
            Users users = invocation.getArgument(0);
            users.setUserId(1L);
            return 1;
        }).when(usersMapper).insert(any(Users.class));

        UsersResponse usersResponse = userService.createUser(usersRequest);

        Assertions.assertEquals(usersRequest.getUserName(), usersResponse.getUserName());
    }

    @Test
    void testCreateUser_throwsDataNotFoundException_WhenRoleNotFound() {
        Mockito.when(rolesMapper.selectByPrimaryKey(usersRequest.getRoleId())).thenReturn(null);

        Assertions.assertThrows(DataNotFoundException.class, () -> userService.createUser(usersRequest));
    }

    @Test
    void testUpdateUser_returnUserResponse() throws Exception {
        Mockito.when(rolesMapper.selectByPrimaryKey(usersRequest.getRoleId())).thenReturn(role);
        Mockito.when(usersMapper.selectByPrimaryKey(user.getUserId())).thenReturn(user);
        Mockito.when(usersMapper.selectByUserName(usersRequest.getUserName())).thenReturn(null);
        Mockito.when(usersMapper.updateByPrimaryKeySelective(any(Users.class))).thenReturn(1);
        Mockito.when(usersMapper.getAllUserDetailByUserId(user.getUserId())).thenReturn(usersFull);

        UsersResponse usersResponse = userService.updateUser(usersFull.getUserId(), usersRequest);

        // Assert
        Assertions.assertNotNull(usersResponse);
        Assertions.assertEquals(usersRequest.getUserName(), usersResponse.getUserName());
    }

    @Test
    void testUpdateUser_throwsDataNotFoundException_WhenRoleNotFound() {
        Mockito.when(rolesMapper.selectByPrimaryKey(usersRequest.getRoleId())).thenReturn(null);
        Assertions.assertThrows(DataNotFoundException.class, () -> userService.updateUser(user.getUserId(), usersRequest));
    }

    @Test
    void testDeleteUser() throws DataNotFoundException {
        Mockito.when(usersMapper.selectByPrimaryKey(user.getUserId())).thenReturn(user);
        Mockito.when(usersMapper.deleteByPrimaryKey(user.getUserId())).thenReturn(1);

        userService.deleteUser(user.getUserId());
        Mockito.verify(usersMapper, Mockito.times(1)).deleteByPrimaryKey(user.getUserId());
        
    }

    @Test
    void testDeleteUser_throwsDataNotFoundException() {
        Long userId = 100L;
        Mockito.when(usersMapper.selectByPrimaryKey(userId)).thenReturn(null);
        Assertions.assertThrows(DataNotFoundException.class, () -> userService.deleteUser(userId));
    }

    @Test
    void testGetAllUsers_returnListUsersResponse() {
        UsersFull usersFull1 = new UsersFull(1L, "John", "john123456", 1L, "Admin");
        UsersFull usersFull2 = new UsersFull(2L, "Jane", "jane123456", 1L, "Admin");
        List<UsersFull> usersFullList = new ArrayList<>();
        usersFullList.add(usersFull1);
        usersFullList.add(usersFull2);

        when(usersMapper.getAllUserDetail()).thenReturn(usersFullList);

        List<UsersResponse> usersResponseList = userService.getAllUsers();

        assertEquals(2, usersResponseList.size());
        assertEquals(usersFull1.getUsername(), usersResponseList.get(0).getUserName());
        assertEquals(usersFull2.getUsername(), usersResponseList.get(1).getUserName());
    }
}