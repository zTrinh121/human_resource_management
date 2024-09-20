package com.hr.management.service.impl;

import com.hr.management.exception.DataNotFoundException;
import com.hr.management.mapper.DepartmentsMapper;
import com.hr.management.mapper.EmployeesMapper;
import com.hr.management.mapper.JobsMapper;
import com.hr.management.mapper.UsersMapper;
import com.hr.management.model.*;
import com.hr.management.request.EmployeesRequest;
import com.hr.management.response.EmployeesResponse;
import com.hr.management.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeesMapper employeesMapper;
    @Autowired
    JobsMapper jobsMapper;
    @Autowired
    DepartmentsMapper departmentsMapper;
    @Autowired
    UsersMapper usersMapper;
    EmployeesExample employeesExample;

    @Override
    public List<EmployeesResponse> getAllEmployees() {
        List<EmployeeFull> employees = employeesMapper.selectEmployeesWithDetails();
        List<EmployeesResponse> employeesResponses = employees
                .stream()
                .map(employee -> EmployeesResponse.fromEmployeeFull(employee))
                .toList();
        return employeesResponses;
    }

    @Override
    public EmployeesResponse getEmployeeById(Long id) {
        return EmployeesResponse.fromEmployeeFull(employeesMapper.selectEmployeesWithDetailsById(id));
    }

    @Override
    public EmployeesResponse createEmployee(EmployeesRequest employeesRequest) throws DataNotFoundException {
        Jobs exisitingJob = jobsMapper.selectByPrimaryKey(employeesRequest.getJobId());

        if(exisitingJob == null){
            throw new DataNotFoundException(String.format(
                    "Employee not found with job ID = %d", exisitingJob.getJobId()));
        }
        if(employeesRequest.getDepartmentId() != null){
            if(departmentsMapper.selectByPrimaryKey(employeesRequest.getDepartmentId()) == null){
                throw new DataNotFoundException(String.format(
                        "Employee not found with department ID = %d", employeesRequest.getDepartmentId()));
            }
        }

        if(employeesRequest.getManagerId() != null){
            if(employeesMapper.selectByPrimaryKey(employeesRequest.getManagerId()) == null){
                throw new DataNotFoundException(String.format(
                        "Employee not found with manager ID = %d", employeesRequest.getManagerId()));
            }
        }

        if(employeesRequest.getUserId() != null){
            Users existingUser = usersMapper.selectByPrimaryKey(employeesRequest.getUserId());
            if(existingUser == null){
                throw new DataNotFoundException(String.format(
                        "Employee not found with user ID = %d", existingUser.getUserId()));
            }
            EmployeeFull existingEmployeesAndUser = employeesMapper.selectByUserId(employeesRequest.getUserId());
            if(existingEmployeesAndUser != null){
                throw new DataNotFoundException(String.format(
                        "Employee is existed with user ID = %d", existingUser.getUserId()));
            }
        }

        if(employeesMapper.selectByEmail(employeesRequest.getEmail()) != null){
            throw new DataNotFoundException(
                    "Employee is existed with email ="+ employeesRequest.getEmail());
        }


        Employees employees = Employees.fromEmployeeRequest(employeesRequest);
        employeesMapper.insertSelective(employees);
        return getEmployeeById(employees.getEmployeeId());
    }

    @Override
    public EmployeesResponse updateEmployee(Long id, EmployeesRequest employeesRequest)throws Exception {
        Employees employees = employeesMapper.selectByEmployeeId(id);
        System.out.println(employees);
        if(employees == null){
            throw new DataNotFoundException(String.format(
                    "Employee is existed with user ID = %d", id));
        }

        employeesMapper.updateByPrimaryKeySelective(Employees.fromEmployeeRequest(employeesRequest));
        EmployeesResponse employeesResponse = getEmployeeById(id);
        return employeesResponse;
    }

    @Override
    public void deleteEmployee(Long id) {
        employeesMapper.deleteByPrimaryKey(id);
    }

    @Override
    public EmployeeFull selectByUserId(Long userId) {
        return employeesMapper.selectByUserId(userId);
    }


}
