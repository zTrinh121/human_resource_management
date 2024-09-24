package com.hr.management.service;

import com.hr.management.exception.DataNotFoundException;
import com.hr.management.model.EmployeeFull;
import com.hr.management.model.Employees;
import com.hr.management.request.EmployeesRequest;
import com.hr.management.response.EmployeesResponse;

import java.util.List;

public interface EmployeeService {
    List<EmployeesResponse> getAllEmployees();

    EmployeesResponse getEmployeeById(Long id);

    EmployeesResponse createEmployee(EmployeesRequest employeesRequest) throws DataNotFoundException;

    EmployeesResponse updateEmployee(Long id, EmployeesRequest employeesRequest) throws Exception;

    void deleteEmployee(Long id) throws DataNotFoundException;

    EmployeeFull selectByUserId(Long userId);

}
