package com.hr.management.service;

import com.hr.management.exception.DataNotFoundException;
import com.hr.management.exception.DepartmentHasAssociatedEmployeeException;
import com.hr.management.request.DepartmentsRequest;
import com.hr.management.response.DepartmentsResponse;

import java.util.List;

public interface DepartmentService {
    DepartmentsResponse getDepartmentById(Long id);

    List<DepartmentsResponse> getAllDepartments();

    DepartmentsResponse createDepartment(DepartmentsRequest departmentsRequest) ;

    DepartmentsResponse updateDepartment(Long id, DepartmentsRequest departmentsRequest) ;

    void deleteDepartment(Long id) throws DataNotFoundException, DepartmentHasAssociatedEmployeeException;

}
