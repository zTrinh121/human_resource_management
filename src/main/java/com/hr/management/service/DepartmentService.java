package com.hr.management.service;

import com.hr.management.exception.DataNotFoundException;
import com.hr.management.request.DepartmentsRequest;
import com.hr.management.response.DepartmentsResponse;

import java.util.List;

public interface DepartmentService {
    DepartmentsResponse getDepartmentById(Long id);

    List<DepartmentsResponse> getAllDepartments();

    DepartmentsResponse createDepartment(DepartmentsRequest departmentsRequest) throws DataNotFoundException;

    DepartmentsResponse updateDepartment(Long id, DepartmentsRequest departmentsRequest) throws Exception;

    void deleteDepartment(Long id) throws DataNotFoundException;

}
