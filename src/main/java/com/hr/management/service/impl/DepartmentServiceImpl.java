package com.hr.management.service.impl;


import com.hr.management.exception.DataNotFoundException;
import com.hr.management.exception.DepartmentHasAssociatedEmployeeException;
import com.hr.management.mapper.DepartmentsMapper;
import com.hr.management.mapper.EmployeesMapper;
import com.hr.management.model.*;
import com.hr.management.request.DepartmentsRequest;
import com.hr.management.response.DepartmentsResponse;
import com.hr.management.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    DepartmentsMapper departmentsMapper;
    @Autowired
    EmployeesMapper employeesMapper;


    @Override
    public DepartmentsResponse getDepartmentById(Long id) {
        DepartmentsFull department = departmentsMapper.getDepartmentsWithManagerNameById(id);
        if (department == null){
            throw new DataNotFoundException("Department not found with ID = " + id);
        }
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
    public DepartmentsResponse createDepartment(DepartmentsRequest departmentsRequest) {
        EmployeeFull existingEmployee = employeesMapper.selectByPrimaryKey(departmentsRequest.getManagerId());
        if (existingEmployee == null) {
            throw new DataNotFoundException("Department not found with manager ID = " + departmentsRequest.getManagerId());
        }

        Departments department = Departments.fromDepartmentRequest(departmentsRequest);
        if(departmentsMapper.getDepartmentByName(department.getDepartmentName()).size() > 0){
            throw new DataIntegrityViolationException("Department name is already existed");
        }
        departmentsMapper.insert(department);

        DepartmentsResponse departmentsResponse = getDepartmentById(department.getDepartmentId());
        return departmentsResponse;
    }

    @Override
    public DepartmentsResponse updateDepartment(Long id, DepartmentsRequest departmentsRequest){
        Departments existingDepartment = departmentsMapper.selectByPrimaryKey(id);
        if(existingDepartment == null){
            throw  new DataNotFoundException("Not found department with ID = " + id);
        }
        existingDepartment.setDepartmentName(departmentsRequest.getDepartmentName());
        existingDepartment.setManagerId(departmentsRequest.getManagerId());

        departmentsMapper.updateByPrimaryKey(existingDepartment);

        return getDepartmentById(existingDepartment.getDepartmentId());
    }

    @Override
    public String deleteDepartment(Long id) throws DepartmentHasAssociatedEmployeeException {
        Departments existingDepartment = departmentsMapper.selectByPrimaryKey(id);
        if(existingDepartment == null){
            throw  new DataNotFoundException("Not found department with id " + id);
        }
        Long countDepartment = employeesMapper.isDepartmentExisting(id);
        if(countDepartment != 0){
        throw new DepartmentHasAssociatedEmployeeException("Cannot delete department with id " + id + " because it is associated with employees.");
        }

        departmentsMapper.deleteByPrimaryKey(id);
        return "Delete department succesfully";
    }

    @Override
    public List<DepartmentsResponse> searchByKeyWord(String keyword) {
        List<DepartmentsFull> departmentsFulls = departmentsMapper.getDepartmentsWithManagerNameByKeyWord(keyword);
        if (departmentsFulls == null) {
            return getAllDepartments();
        }
        return departmentsFulls.stream()
                .map(DepartmentsResponse::fromDepartmentFull)
                .toList();
    }
}
