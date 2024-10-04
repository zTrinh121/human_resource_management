package com.hr.management.service.impl;

import com.hr.management.component.JwtTokenUtil;
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
import com.hr.management.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UsersMapper usersMapper;
    private final RolesMapper rolesMapper;
    private final EmployeesMapper employeesMapper;

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    @Override
    public UsersResponse getUserById(Long id)  {
        UsersFull users = usersMapper.getAllUserDetailByUserId(id);
        if (users == null) {
            throw new DataNotFoundException(String.format("User with ID = %d cannot found", id));
            
        }
        return UsersResponse.fromUserFull(users);
    }

    @Override
    public List<UsersResponse> getAllUsers() {
        List<UsersFull> usersFullList = usersMapper.getAllUserDetail();
        return usersFullList.stream()
                .map(UsersResponse::fromUserFull)
                .toList();
    }

    @Override
    public UsersResponse createUser(UsersRequest usersRequest) throws MappingException {
        //Check role existing or not
        Roles existingRole = rolesMapper.selectByPrimaryKey(usersRequest.getRoleId());
        if(existingRole == null){
            throw new DataNotFoundException(String.format("Role not found with ID = %d",
                    usersRequest.getRoleId()));
        }

        Users existingUser = usersMapper.selectByUserName(usersRequest.getUserName());
        if(existingUser != null){
            throw new DataIntegrityViolationException("User name has existed");
        }

        Users user = Users.fromUserRequest(usersRequest);

        String password = usersRequest.getPassword();
        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);

        usersMapper.insert(user);

        //Check whether the email is existing or not
        EmployeeFull emailExistingEmployee = employeesMapper.selectByEmail(usersRequest.getEmail());
        if(emailExistingEmployee == null){
            deleteUser(user.getUserId());
            throw new DataNotFoundException("Employee not found with email = " + usersRequest.getEmail());
        }
        //Check whether the employee with email is already mapped to the user 
        if(emailExistingEmployee.getUserId() != null){
            deleteUser(user.getUserId());
            throw new MappingException("Cannot mapping employee with user.Employee has already been mapped to other user");
        }

        employeesMapper.setUserIdForEmployee(emailExistingEmployee.getEmployeeId(), user.getUserId());
        return getUserById(user.getUserId());

    }

    @Override
    public UsersResponse updateUser(Long id, UsersRequest usersRequest) {
        //Check role existing or not
        Roles existingRole = rolesMapper.selectByPrimaryKey(usersRequest.getRoleId());
        if(existingRole == null){
            throw new DataNotFoundException(String.format("Role not found with ID = %d",
                    usersRequest.getRoleId()));
        }

        //Check userId is existed or not
        Users existingUser = usersMapper.selectByPrimaryKey(id);
        if(existingUser == null){
            throw new DataNotFoundException(String.format("User not found with ID = %d",
                    id));
        }

        //Check userName is existed or not
        Users existingUserName = usersMapper.selectByUserName(usersRequest.getUserName());
        if(existingUserName != null &&
        existingUserName.getUserName().equals(usersRequest.getUserName()) &&
                !Objects.equals(existingUserName.getUserId(), id)){
            throw new DataNotFoundException("User name has existed");
        }

        //Email filled or not
        if(!usersRequest.getEmail().isEmpty()){
            EmployeeFull emailExistingEmployee = employeesMapper.selectByEmail(usersRequest.getEmail());
            if(emailExistingEmployee == null){
                throw new DataNotFoundException("Employee not found with email = " + usersRequest.getEmail());
            }

            EmployeeFull existingEmployeesAndUser = employeesMapper.selectByUserId(id);
            if(!existingEmployeesAndUser.getEmail().equals(usersRequest.getEmail())){
                throw new DataIntegrityViolationException("Cannot change email");
            }

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

        EmployeeFull employees = employeesMapper.selectByUserId(id);
        if(employees != null){
            employeesMapper.removingUserIdForEmployee(employees.getUserId());
        }

        usersMapper.deleteByPrimaryKey(id);

    }

    @Override
    public String login(String username, String password) throws Exception {
        UsersFull user = usersMapper.getAllUserDetailByUserName(username);
        if (user == null){
            throw new DataNotFoundException("Invalid username/password");
        }
        //Check password=
        if(!passwordEncoder.matches(password, user.getPassword())){
            throw new BadCredentialsException("Wrong username or password");
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                username,
                password,
                user.getAuthorities()
        );
        authenticationManager.authenticate(authenticationToken);

        return jwtTokenUtil.generateToken(user);
    }
}
