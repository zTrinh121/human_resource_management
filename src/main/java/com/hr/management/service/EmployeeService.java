package com.hr.management.service;

import com.hr.management.exception.DataNotFoundException;
import com.hr.management.exception.MappingException;
import com.hr.management.model.EmployeeFull;
import com.hr.management.request.EmployeesRequest;
import com.hr.management.response.EmployeesResponse;

import java.util.List;

public interface EmployeeService {
    List<EmployeesResponse> getAllEmployees();

    EmployeesResponse getEmployeeById(Long id);

    EmployeesResponse createEmployee(EmployeesRequest employeesRequest) ;

    EmployeesResponse updateEmployee(Long id, EmployeesRequest employeesRequest) ;

    void deleteEmployee(Long id) throws DataNotFoundException;

    void deleteSoftEmployee(Long id);

    EmployeeFull selectByUserId(Long userId);

    EmployeesResponse mappingEmployeeWithUser(Long employeeId, Long userId) throws MappingException;

    List<EmployeesResponse> searchByKeyWord(String keyword, String department);



    

}
