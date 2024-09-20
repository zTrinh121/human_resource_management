package com.hr.management.service.impl;


import com.hr.management.exception.DataNotFoundException;
import com.hr.management.mapper.DepartmentsMapper;
import com.hr.management.mapper.EmployeesMapper;
import com.hr.management.model.*;
import com.hr.management.request.DepartmentsRequest;
import com.hr.management.response.DepartmentsResponse;
import com.hr.management.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentsMapper departmentsMapper;
    @Autowired
    EmployeesMapper employeesMapper;

    DepartmentsExample departmentsExample;
    EmployeesExample employeesExample;

    @Override
    public DepartmentsResponse getDepartmentById(Long id) {
        DepartmentsFull department = departmentsMapper.getDepartmentsWithManagerNameById(id);
        if (department == null) return null;
        return DepartmentsResponse.fromDepartmentFull(department);
    }

    @Override
    public List<DepartmentsResponse> getAllDepartments() {
        List<DepartmentsFull> departmentsList = departmentsMapper.getDepartmentsWithManagerName();

        return departmentsList.stream()
                .map(DepartmentsResponse::fromDepartmentFull)
                .toList();
    }

    @Override
    public DepartmentsResponse createDepartment(DepartmentsRequest departmentsRequest) throws DataNotFoundException {
        EmployeeFull existingEmployee = employeesMapper.selectByPrimaryKey(departmentsRequest.getManagerId());
        if (existingEmployee == null) {
            throw new DataNotFoundException(String.format("Not found department with manager id = %d", departmentsRequest.getManagerId()));
            //return null;
        }

        Departments department = Departments.fromDepartmentRequest(departmentsRequest);
        departmentsMapper.insert(department);

        DepartmentsResponse departmentsResponse = getDepartmentById(department.getDepartmentId());
        return departmentsResponse;
    }

    @Override
    public DepartmentsResponse updateDepartment(Long id, DepartmentsRequest departmentsRequest) throws Exception {
        Departments existingDepartment = departmentsMapper.selectByPrimaryKey(id);
        if(existingDepartment == null){
            throw  new DataNotFoundException("Not found department with id " + departmentsRequest.getManagerId());
        }
        existingDepartment.setDepartmentName(departmentsRequest.getDepartmentName());
        existingDepartment.setManagerId(departmentsRequest.getManagerId());

        departmentsMapper.updateByPrimaryKey(existingDepartment);

        return getDepartmentById(existingDepartment.getDepartmentId());
    }

    @Override
    public void deleteDepartment(Long id) throws DataNotFoundException {
        Departments existingDepartment = departmentsMapper.selectByPrimaryKey(id);
        if(existingDepartment == null){
            throw  new DataNotFoundException("Not found department with id " + id);
        }
        departmentsMapper.deleteByPrimaryKey(id);
    }
}
