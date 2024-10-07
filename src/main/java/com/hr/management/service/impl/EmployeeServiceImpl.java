package com.hr.management.service.impl;

import com.hr.management.exception.DataNotFoundException;
import com.hr.management.exception.MappingException;
import com.hr.management.mapper.DepartmentsMapper;
import com.hr.management.mapper.EmployeesMapper;
import com.hr.management.mapper.JobsMapper;
import com.hr.management.mapper.UsersMapper;
import com.hr.management.model.*;
import com.hr.management.request.EmployeesRequest;
import com.hr.management.response.EmployeesResponse;
import com.hr.management.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeesMapper employeesMapper;
    private final JobsMapper jobsMapper;
    private final DepartmentsMapper departmentsMapper;
    private final UsersMapper usersMapper;

    @Override
    public List<EmployeesResponse> getAllEmployees() {
        List<EmployeeFull> employees = employeesMapper.selectEmployeesWithDetails();
        if (employees == null) {
            throw new DataNotFoundException("No employees list found");
        }
        return employees
                .stream()
                .map(EmployeesResponse::fromEmployeeFull)
                .toList();
    }

    @Override
    public EmployeesResponse getEmployeeById(Long id) {
        if (employeesMapper.selectEmployeesWithDetailsById(id) == null) {
            throw new DataNotFoundException(String.format(
                    "Employee not found with ID = %d", id));
        }
        return EmployeesResponse.fromEmployeeFull(employeesMapper.selectEmployeesWithDetailsById(id));
    }

    @Override
    public EmployeesResponse createEmployee(EmployeesRequest employeesRequest) throws DataNotFoundException {
        EmployeeFull existingEmployeesAndUser = isItemFound(employeesRequest.getJobId(),
                employeesRequest.getDepartmentId(),
                employeesRequest.getManagerId(),
                employeesRequest.getUserId());

        if (existingEmployeesAndUser != null) {
            throw new DataNotFoundException(String.format(
                    "User ID = %d has been mapped with an employee ", existingEmployeesAndUser.getUserId()));
        }

        // Check whether email is existing or not
        if (employeesMapper.selectByEmail(employeesRequest.getEmail()) != null) {
            throw new DataNotFoundException(
                    "Employee is existed with email =" + employeesRequest.getEmail());
        }

        Employees employees = Employees.fromEmployeeRequest(employeesRequest);
        employeesMapper.insert(employees);
        return EmployeesResponse.fromEmployee(employees);
    }

    @Override
    public EmployeesResponse updateEmployee(Long id, EmployeesRequest employeesRequest) {

        // Check whether the employee exists or not
        EmployeeFull employeeFull = employeesMapper.selectEmployeesWithDetailsById(id);
        if (employeeFull == null) {
            throw new DataNotFoundException(String.format(
                    "Employee not found with ID = %d", id));
        }

        EmployeeFull existingEmployeesAndUser = isItemFound(employeesRequest.getJobId(),
                employeesRequest.getDepartmentId(),
                employeesRequest.getManagerId(),
                employeeFull.getUserId());

        if (existingEmployeesAndUser != null
                && !Objects.equals(existingEmployeesAndUser.getEmployeeId(), id)) {
            throw new DataNotFoundException(String.format(
                    "User ID = %d has been mapped with an employee ", existingEmployeesAndUser.getUserId()));
        }

        // Check whether email is existing or not
        if (employeesMapper.selectByEmail(employeesRequest.getEmail()) != null
                && !Objects.equals(employeeFull.getEmployeeId(), id)) {
            throw new DataNotFoundException(
                    "Employee is existed with email =" + employeesRequest.getEmail());
        }

        employeesMapper.updateByPrimaryKeySelective(Employees.fromEmployeeRequest(employeesRequest));
        return getEmployeeById(id);
    }

    @Override
    public void deleteSoftEmployee(Long id) {
        if (employeesMapper.selectEmployeesWithDetailsById(id) == null) {
            throw new DataNotFoundException(String.format(
                    "Employee not found with ID = %d", id));
        }
        employeesMapper.deleteSoftEmployee(id);
    }

    @Override
    public void deleteEmployee(Long id) throws DataNotFoundException {
        if (employeesMapper.selectEmployeesWithDetailsById(id) == null) {
            throw new DataNotFoundException(String.format(
                    "Employee not found with ID = %d", id));
        }

        employeesMapper.deleteByPrimaryKey(id);
    }

    @Override
    public EmployeeFull selectByUserId(Long userId) {
        return employeesMapper.selectByUserId(userId);
    }

    @Override
    public EmployeesResponse mappingEmployeeWithUser(Long employeeId, Long userId) throws MappingException {
        // List<EmployeeFull> employeeFulls = employeesMapper.selectByUserIdNull();
        getEmployeeById(employeeId);

        // Check whether userId is existing or not
        Users existingUser = usersMapper.selectByPrimaryKey(userId);
        if (existingUser == null) {
            throw new DataNotFoundException(String.format(
                    "User not found with user ID = %d", userId));
        }

        // Check whether userId is assigned for any employee or not
        EmployeeFull existingEmployeesAndUser = employeesMapper.selectByUserId(userId);
        if (existingEmployeesAndUser != null) {
            throw new DataNotFoundException(String.format(
                    "Employee is existed with user ID = %d", existingUser.getUserId()));
        }

        Long count = employeesMapper.setUserIdForEmployee(employeeId, userId);
        if (count == 0) {
            throw new MappingException("Cannot mapping employee with user");
        }
        return getEmployeeById(employeeId);
    }

    @Override
    public List<EmployeesResponse> searchByKeyWord(String keyword, String departmentName) {
        List<EmployeeFull> employees;
        if (keyword.isBlank() && departmentName.isBlank()) {
            return getAllEmployees();

        }else if (!keyword.isBlank() && !departmentName.isBlank()){
            employees = employeesMapper.searchByKeyWordAndDepartment(keyword, departmentName);
            if (employees == null) {
                throw new DataNotFoundException("No employees list found");
            }
            return employees
                    .stream()
                    .map(EmployeesResponse::fromEmployeeFull)
                    .toList();
        }
        else if (keyword.isBlank()) {
            employees = employeesMapper.searchByDepartment(departmentName);
            if (employees == null) {
                throw new DataNotFoundException("No employees list found");
            }
            return employees
                    .stream()
                    .map(EmployeesResponse::fromEmployeeFull)
                    .toList();

        }
            employees = employeesMapper.searchByKeyWord(keyword);
            if (employees == null) {
                throw new DataNotFoundException("No employees list found");
            }
            return employees
                    .stream()
                    .map(EmployeesResponse::fromEmployeeFull)
                    .toList();

    }


    public EmployeeFull isItemFound(Long jobId, Long departmentId, Long managerId, Long userId) {
        // Check whether jobId is existing or not
        if (jobId != null &&
                jobsMapper.selectByPrimaryKey(jobId) == null) {
            throw new DataNotFoundException(String.format(
                    "Job not found with job ID = %d", jobId));
        }

        // Check whether departmentId is existing or not
        if (departmentId != null &&
                departmentsMapper.selectByPrimaryKey(departmentId) == null) {
            throw new DataNotFoundException(String.format(
                    "Department not found with department ID = %d", departmentId));
        }

        // Check whether managerId is existing or not
        if (managerId != null &&
                employeesMapper.selectEmployeesWithDetailsById(managerId) == null) {
            throw new DataNotFoundException(String.format(
                    "Manager not found with manager ID = %d", managerId));
        }

        // Check whether userId is existing or not
        if (userId != null
                && usersMapper.selectByPrimaryKey(userId) == null) {
            throw new DataNotFoundException(String.format(
                    "User not found with user ID = %d", userId));
        }

        // Check whether userId has already been mapped to an employee or not
        return employeesMapper.selectByUserId(userId);
    }

}
