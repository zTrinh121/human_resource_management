package com.hr.management.serviceImpl;

import com.hr.management.response.DepartmentsResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hr.management.exception.DataNotFoundException;
import com.hr.management.mapper.DepartmentsMapper;
import com.hr.management.mapper.EmployeesMapper;
import com.hr.management.model.Departments;
import com.hr.management.model.DepartmentsFull;
import com.hr.management.model.EmployeeFull;
import com.hr.management.request.DepartmentsRequest;
import com.hr.management.service.impl.DepartmentServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTest {
    @Mock
    private DepartmentsMapper departmentsMapper;
    @Mock 
    private EmployeesMapper employeesMapper;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    private DepartmentsFull departmentsFull;
    private DepartmentsFull departmentsFull2;
    private EmployeeFull employeeFull;

    @BeforeEach
    void setUp(){
        departmentsFull = new DepartmentsFull(1L, "IT", 1L, "John Doe");
        departmentsFull2 = new DepartmentsFull(1L, "Sale", 1L, "Jane Doe");
        employeeFull = new EmployeeFull(
                    1L,
                    "John",
                    "Doe",
                    "john.doe@example.com",
                    "123-456-7890",
                    LocalDate.of(1990, 1, 1),
                    LocalDate.of(2020, 5, 1),
                    60000.00f,
                "ACTIVE",
                    1L,
                    3L,
                    4L,
                    5L,
                    "Jane Smith",
                    "Software Engineer",
                    "Engineering"
        );
    }

    @Test
    void testGetDepartmentById_returnDepartmentsFull() {
        when(departmentsMapper.getDepartmentsWithManagerNameById(departmentsFull.getDepartmentId()))
        .thenReturn(departmentsFull);

        assertEquals(departmentService.getDepartmentById(departmentsFull.getDepartmentId()).getDepartmentName(),
                departmentsFull.getDepartmentName());
    }

    @Test
    void testGetDepartmentById_returnDepartmentsResponse(){
        when(departmentsMapper.getDepartmentsWithManagerNameById(departmentsFull.getDepartmentId()))
       .thenReturn(departmentsFull);

       assertEquals(departmentService.getDepartmentById(departmentsFull.getDepartmentId()).getDepartmentName(),
                departmentsFull.getDepartmentName());
    }

    @Test
    void testGetAllDepartments_returnDepartmentsResponse(){
        List<DepartmentsFull> departments = new ArrayList<>();
        departments.add(departmentsFull);
        departments.add(departmentsFull2);

        when(departmentsMapper.getDepartmentsWithManagerName())
        .thenReturn(departments);

        List<DepartmentsResponse> departmentsResponses = departmentService.getAllDepartments();
        assertEquals(2, departmentsResponses.size());
        assertEquals(departmentsFull.getDepartmentName(), departmentsResponses.get(0).getDepartmentName());
        assertEquals(departmentsFull2.getDepartmentName(), departmentsResponses.get(1).getDepartmentName());
    }

    @Test
    void testCreateApartment_returnDepartmentsResponse() throws DataNotFoundException {
        when(employeesMapper.selectByPrimaryKey(departmentsFull.getManagerId())).thenReturn(employeeFull);
        doAnswer(invocation -> {
            Departments departments = invocation.getArgument(0);
            departments.setDepartmentId(1L);
            return 1;
        }).when(departmentsMapper).insert(any(Departments.class));
        when(departmentsMapper.getDepartmentsWithManagerNameById(departmentsFull.getDepartmentId())).thenReturn(departmentsFull);

        DepartmentsResponse departmentsResponse = departmentService.createDepartment(DepartmentsRequest.fromDepartmentFull(departmentsFull));
        assertEquals(departmentsFull.getDepartmentName(), departmentsResponse.getDepartmentName());
    }

    @Test
    void testCreateEmployee_ExceptionEmployeeNotFound() throws DataNotFoundException {
        DataNotFoundException exception = assertThrows(DataNotFoundException.class,
                () -> departmentService.createDepartment(DepartmentsRequest.fromDepartmentFull(departmentsFull)));

        assertEquals(String.format("Department not found with manager ID = %d", departmentsFull.getManagerId()),
                exception.getMessage());
    }

    @Test
    void testUpdateDepartment_updateDepartment() throws Exception {
        when(departmentsMapper.selectByPrimaryKey(departmentsFull.getDepartmentId())).thenReturn(Departments.fromDepartmentFull(departmentsFull));
        doReturn(1).when(departmentsMapper).updateByPrimaryKey(any(Departments.class));
        when(departmentsMapper.getDepartmentsWithManagerNameById(departmentsFull.getDepartmentId())).thenReturn(departmentsFull);

        DepartmentsResponse departmentsResponse = departmentService.updateDepartment(departmentsFull.getDepartmentId(), DepartmentsRequest.fromDepartmentFull(departmentsFull));
        assertEquals(departmentsFull.getDepartmentName(), departmentsResponse.getDepartmentName());
    }

    @Test
    void testDeleteDepartment() {
        Long userId = 100L;
        Mockito.when(departmentsMapper.selectByPrimaryKey(userId)).thenReturn(null);
        assertThrows(DataNotFoundException.class, () -> departmentService.deleteDepartment(100L));
    }

}
