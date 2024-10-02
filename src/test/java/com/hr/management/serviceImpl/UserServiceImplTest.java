package com.hr.management.serviceImpl;

import com.hr.management.exception.DataNotFoundException;
import com.hr.management.exception.MappingException;
import com.hr.management.mapper.EmployeesMapper;
import com.hr.management.mapper.RolesMapper;
import com.hr.management.mapper.UsersMapper;
import com.hr.management.model.EmployeeFull;
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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UsersMapper usersMapper;

    @Mock
    private RolesMapper rolesMapper;
    @Mock
    private EmployeesMapper employeesMapper;

    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    private Users user;
    private Roles role;
    private UsersFull usersFull;
    private UsersRequest usersRequest;
    private EmployeeFull employeeFull;

    @BeforeEach
    void setUp() {
        user = new Users(1L, "John", "john123456", 1L);
        role = new Roles(1L, "Admin");
        employeeFull = new EmployeeFull(
                1L,
                "John",
                "Doe",
                "johndoe@example.com",
                "123-456-7890",
                LocalDate.of(1990, 1, 1),
                LocalDate.of(2020, 5, 1),
                60000.00f,
                "ACTIVE",
                2L,
                3L,
                4L,
                null,
                "Jane Smith",
                "Software Engineer",
                "Engineering"

        );
        usersRequest = new UsersRequest("John", "johndoe@example.com", "john123456", 1L);
        usersFull = new UsersFull(1L, "John", "john123456", 1L, "Admin");
    }

    @Test
    void testGetUserById_returnUserResponse() {
        Mockito.when(usersMapper.getAllUserDetailByUserId(user.getUserId()))
                .thenReturn(usersFull);

        UsersResponse usersResponse = userService.getUserById(user.getUserId());

        Assertions.assertNotNull(usersResponse);
        Assertions.assertEquals(user.getUserName(), usersResponse.getUserName());
    }

    @Test
    void testGetUserById_throwsDataNotFoundException() {
        Mockito.when(usersMapper.getAllUserDetailByUserId(1L)).thenReturn(null);

        assertThrows(DataNotFoundException.class, () -> userService.getUserById(1L));
    }

    @Test
    void testCreateUser_returnUserResponse() throws MappingException {
        when(rolesMapper.selectByPrimaryKey(role.getRoleId())).thenReturn(role);
        when(employeesMapper.selectByEmail(usersRequest.getEmail())).thenReturn(employeeFull);
        when(usersMapper.getAllUserDetailByUserId(user.getUserId())).thenReturn(usersFull);
        when(passwordEncoder.encode(usersRequest.getPassword())).thenReturn("encodedPassword");

        doAnswer(invocation -> {
            Users users = invocation.getArgument(0);
            users.setUserId(1L);
            return 1;
        }).when(usersMapper).insert(any(Users.class));

        UsersResponse usersResponse = userService.createUser(usersRequest);

        Assertions.assertEquals(usersRequest.getUserName(), usersResponse.getUserName());
    }

    @Test
    void testCreateUser_emailAlreadyMapped()  {
        when(rolesMapper.selectByPrimaryKey(role.getRoleId())).thenReturn(role);
        when(passwordEncoder.encode(usersRequest.getPassword())).thenReturn("encodedPassword");
        when(usersMapper.selectByPrimaryKey(user.getUserId())).thenReturn(user);
        when(employeesMapper.selectByEmail(usersRequest.getEmail())).thenReturn(employeeFull);

        doAnswer(invocation -> {
            Users users = invocation.getArgument(0);
            employeeFull.setUserId(3L);
            users.setUserId(1L);

            return 1;
        }).when(usersMapper).insert(any(Users.class));

        MappingException exception = assertThrows(MappingException.class,
                () -> userService.createUser(usersRequest));

        assertEquals("Cannot mapping employee with user.Employee has already been mapped to other user",
                exception.getMessage());
    }

    @Test
    void testCreateUser_alreadyMapped()  {
        when(rolesMapper.selectByPrimaryKey(role.getRoleId())).thenReturn(role);
        when(passwordEncoder.encode(usersRequest.getPassword())).thenReturn("encodedPassword");
        when(usersMapper.selectByPrimaryKey(user.getUserId())).thenReturn(user);
        when(employeesMapper.selectByEmail(usersRequest.getEmail())).thenReturn(employeeFull);

        doAnswer(invocation -> {
            Users users = invocation.getArgument(0);
            employeeFull.setUserId(3L);
            users.setUserId(1L);

            return 1;
        }).when(usersMapper).insert(any(Users.class));

        MappingException exception = assertThrows(MappingException.class,
                () -> userService.createUser(usersRequest));

        assertEquals("Cannot mapping employee with user.Employee has already been mapped to other user",
                exception.getMessage());
    }


    @Test
    void testCreateUser_EmailNotFound()  {
        when(rolesMapper.selectByPrimaryKey(role.getRoleId())).thenReturn(role);
        when(passwordEncoder.encode(usersRequest.getPassword())).thenReturn("encodedPassword");
        when(usersMapper.selectByPrimaryKey(user.getUserId())).thenReturn(user);

        doAnswer(invocation -> {
            Users users = invocation.getArgument(0);
            users.setUserId(1L);
            return 1;
        }).when(usersMapper).insert(any(Users.class));

        DataNotFoundException exception = assertThrows(DataNotFoundException.class,
                () -> userService.createUser(usersRequest));

        assertEquals(String.format("Employee not found with email = %s", usersRequest.getEmail()),
                exception.getMessage());
    }

    @Test
    void testCreateUser_UserHasExisted()  {
        when(rolesMapper.selectByPrimaryKey(role.getRoleId())).thenReturn(role);
        when(usersMapper.selectByUserName(user.getUserName())).thenReturn(user);
        DataIntegrityViolationException exception = assertThrows(DataIntegrityViolationException.class,
                () -> userService.createUser(usersRequest));

        assertEquals("User name has existed",
                exception.getMessage());
    }

    @Test
    void testCreateUser_RoleNotFound() {

        DataNotFoundException exception = assertThrows(DataNotFoundException.class,
                () -> userService.createUser(usersRequest));

        assertEquals(String.format("Role not found with ID = %d", usersRequest.getRoleId()),
                exception.getMessage());
    }

    @Test
    void testUpdateUser_returnUserResponse()  {
        when(rolesMapper.selectByPrimaryKey(usersRequest.getRoleId())).thenReturn(role);
        when(usersMapper.selectByPrimaryKey(user.getUserId())).thenReturn(user);
        when(usersMapper.selectByUserName(usersRequest.getUserName())).thenReturn(null);
        when(usersMapper.updateByPrimaryKeySelective(any(Users.class))).thenReturn(1);
        when(usersMapper.getAllUserDetailByUserId(user.getUserId())).thenReturn(usersFull);
        when(employeesMapper.selectByEmail(usersRequest.getEmail())).thenReturn(employeeFull);
        when(employeesMapper.selectByUserId(user.getUserId())).thenReturn(employeeFull);

        UsersResponse usersResponse = userService.updateUser(usersFull.getUserId(), usersRequest);

        Assertions.assertNotNull(usersResponse);
        Assertions.assertEquals(usersRequest.getUserName(), usersResponse.getUserName());
    }

    @Test
    void testUpdateUser_WhenUserNotFound()  {
        when(rolesMapper.selectByPrimaryKey(usersRequest.getRoleId())).thenReturn(role);

        DataNotFoundException exception = assertThrows(DataNotFoundException.class,
                () -> userService.updateUser(user.getUserId(), usersRequest));

        assertEquals(String.format("User not found with ID = %d", user.getUserId()),
                exception.getMessage());
    }

    @Test
    void testUpdateUser_throwsDataNotFoundException_WhenRoleNotFound() {
        DataNotFoundException exception = assertThrows(DataNotFoundException.class,
                () -> userService.updateUser(user.getUserId(), usersRequest));

        assertEquals(String.format("Role not found with ID = %d", user.getRoleId()),
                exception.getMessage());
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
        assertThrows(DataNotFoundException.class, () -> userService.deleteUser(userId));
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