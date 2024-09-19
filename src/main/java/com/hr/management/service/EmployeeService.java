package com.hr.management.service;

import com.hr.management.model.EmployeeFull;
import com.hr.management.model.Employees;
import com.hr.management.response.EmployeesResponse;

import java.util.List;

public interface EmployeeService {
    List<Employees> getAllEmployees();

    EmployeesResponse getEmployeeById(Long id);

    List<EmployeesResponse> selectEmployeesWithDetails();
}
