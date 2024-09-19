package com.hr.management.service.impl;

import com.hr.management.mapper.EmployeesMapper;
import com.hr.management.model.EmployeeFull;
import com.hr.management.model.Employees;
import com.hr.management.model.EmployeesExample;
import com.hr.management.response.EmployeesResponse;
import com.hr.management.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeesMapper employeesMapper;
    EmployeesExample employeesExample;

    @Override
    public List<Employees> getAllEmployees() {

        List<Employees> employees = employeesMapper.selectByExample(employeesExample);
        return employees;
    }

    @Override
    public EmployeesResponse getEmployeeById(Long id) {
        EmployeeFull employeeFull = employeesMapper.selectEmployeesWithDetailsById(id);
        return EmployeesResponse.fromEmployeeFull(employeeFull);
    }

    @Override
    public List<EmployeesResponse> selectEmployeesWithDetails() {

        List<EmployeeFull> employeeFulls = employeesMapper.selectEmployeesWithDetails();
        List<EmployeesResponse> employeesResponses = employeeFulls.stream()
                .map(EmployeesResponse::fromEmployeeFull)
                .toList();
        return employeesResponses;
    }
}
