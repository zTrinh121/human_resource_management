package com.hr.management.serviceImpl;

import com.hr.management.exception.DataNotFoundException;

import com.hr.management.exception.MappingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hr.management.mapper.DepartmentsMapper;
import com.hr.management.mapper.EmployeesMapper;
import com.hr.management.mapper.JobsMapper;
import com.hr.management.mapper.UsersMapper;
import com.hr.management.model.Departments;
import com.hr.management.model.EmployeeFull;
import com.hr.management.model.Jobs;
import com.hr.management.model.Users;
import com.hr.management.request.EmployeesRequest;
import com.hr.management.response.EmployeesResponse;
import com.hr.management.service.impl.EmployeeServiceImpl;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {

    @Mock
    private EmployeesMapper employeesMapper;
    @Mock
    private JobsMapper jobsMapper;
    @Mock
    private DepartmentsMapper departmentsMapper;
    @Mock
    private UsersMapper usersMapper;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    // private Employees employee;
    private EmployeeFull employeeFull1;
    private EmployeeFull employeeFull2;
    private EmployeesRequest employeeRequest;
    private Jobs job;
    private Departments department;
    private Users user;

    @BeforeEach
    void setUp() {
        employeeFull1 = new EmployeeFull(
                1L,
                "John",
                "Doe",
                "john.doe@example.com",
                "123-456-7890",
                LocalDate.of(1990, 1, 1),
                LocalDate.of(2020, 5, 1),
                60000.00f,
                "ACTIVE",
                2L,
                3L,
                4L,
                5L,
                "Jane Smith",
                "Software Engineer",
                "Engineering"

        );
        employeeFull2 = new EmployeeFull(
                2L,
                "Jane",
                "Doe",
                "jane.doe@example.com",
                "123-456-7890",
                LocalDate.of(1992, 1, 1),
                LocalDate.of(2022, 5, 1),
                50000.00f,
                "ACTIVE",
                2L,
                3L,
                4L,
                5L,
                "John Smith",
                "Software Engineer",
                "Engineering");

        employeeRequest = new EmployeesRequest(
                "John",
                "Doe",
                "john.doe@example.com",
                "123-456-7890",
                LocalDate.of(1990, 1, 1),
                LocalDate.of(2020, 5, 1),
                60000.00f,

                2L,
                3L,
                4L,
                5L,
                "ACTIVE");

        job = new Jobs(1L, "Manager");
        department = new Departments(3L, "Engineering", 2L);
        user = new Users(5L, "Jane Smith", "janesmith123456", 2L);
    }

    @Test
    void testGetEmployeeById_returnEmployee() {
        when(employeesMapper.selectEmployeesWithDetailsById(employeeFull1.getEmployeeId())).thenReturn(employeeFull1);

        EmployeesResponse employeesResponse = employeeService.getEmployeeById(employeeFull1.getEmployeeId());
        assertNotNull(employeesResponse);
        assertEquals(employeeFull1.getEmployeeId(), employeesResponse.getEmployeeId());
    }

    @Test
    void testGetEmployeeById_DataNotFoundException() {

        DataNotFoundException exception = assertThrows(DataNotFoundException.class,
                () -> employeeService.getEmployeeById(employeeFull1.getEmployeeId()));

        assertEquals(String.format("Employee not found with ID = %d", employeeFull1.getEmployeeId()),
                exception.getMessage());
    }

    @Test
    void testGetAllEmployees() {
        when(employeesMapper.selectEmployeesWithDetails())
                .thenReturn(List.of(employeeFull1, employeeFull2));

        List<EmployeesResponse> employeesResponses = employeeService.getAllEmployees();
        assertNotNull(employeesResponses);
        assertEquals(2, employeesResponses.size());
        assertTrue(employeesResponses.contains(EmployeesResponse.fromEmployeeFull(employeeFull1)));
        assertTrue(employeesResponses.contains(EmployeesResponse.fromEmployeeFull(employeeFull2)));
    }

    @Test
    void testCreateEmployee() throws DataNotFoundException {
        when(jobsMapper.selectByPrimaryKey(employeeRequest.getJobId())).thenReturn(job);
        when(departmentsMapper.selectByPrimaryKey(employeeRequest.getDepartmentId())).thenReturn(department);
        when(employeesMapper.selectEmployeesWithDetailsById(employeeFull2.getEmployeeId())).thenReturn(employeeFull2);
        when(usersMapper.selectByPrimaryKey(employeeRequest.getUserId())).thenReturn(user);

        EmployeesResponse employeesResponse = employeeService.createEmployee(employeeRequest);

        assertNotNull(employeesResponse);
        assertEquals(employeeRequest.getFirstName(), employeesResponse.getFirstName());
        assertEquals(employeeRequest.getLastName(), employeesResponse.getLastName());
    }

    @Test
    void testCreateEmployee_ExceptionJobNotFound() throws DataNotFoundException {
        DataNotFoundException exception = assertThrows(DataNotFoundException.class,
                () -> employeeService.createEmployee(employeeRequest));

        assertEquals(String.format("Job not found with job ID = %d", employeeFull1.getJobId()),
                exception.getMessage());
    }

    @Test
    void testCreateEmployee_ExceptionDepartmentNotFound() throws DataNotFoundException {
        when(jobsMapper.selectByPrimaryKey(employeeRequest.getJobId())).thenReturn(job);
        DataNotFoundException exception = assertThrows(DataNotFoundException.class,
                () -> employeeService.createEmployee(employeeRequest));

        assertEquals(String.format("Department not found with department ID = %d", employeeFull1.getDepartmentId()),
                exception.getMessage());
    }

    @Test
    void testCreateEmployee_ExceptionManagerNotFound() throws DataNotFoundException {
        when(jobsMapper.selectByPrimaryKey(employeeRequest.getJobId())).thenReturn(job);
        when(departmentsMapper.selectByPrimaryKey(employeeRequest.getDepartmentId())).thenReturn(department);
        DataNotFoundException exception = assertThrows(DataNotFoundException.class,
                () -> employeeService.createEmployee(employeeRequest));

        assertEquals(String.format("Manager not found with manager ID = %d", employeeFull1.getManagerId()),
                exception.getMessage());
    }

    @Test
    void testCreateEmployee_ExceptionUserNotFound() throws DataNotFoundException {
        when(jobsMapper.selectByPrimaryKey(employeeRequest.getJobId())).thenReturn(job);
        when(departmentsMapper.selectByPrimaryKey(employeeRequest.getDepartmentId())).thenReturn(department);
        when(employeesMapper.selectEmployeesWithDetailsById(employeeFull2.getEmployeeId())).thenReturn(employeeFull2);
        DataNotFoundException exception = assertThrows(DataNotFoundException.class,
                () -> employeeService.createEmployee(employeeRequest));

        assertEquals(String.format("User not found with user ID = %d", employeeFull1.getUserId()),
                exception.getMessage());
    }

    @Test
    void testUpdateEmployee() {
        when(jobsMapper.selectByPrimaryKey(employeeRequest.getJobId())).thenReturn(job);
        when(departmentsMapper.selectByPrimaryKey(employeeRequest.getDepartmentId())).thenReturn(department);
        when(employeesMapper.selectEmployeesWithDetailsById(employeeFull2.getEmployeeId())).thenReturn(employeeFull2);
        when(employeesMapper.selectEmployeesWithDetailsById(employeeFull1.getEmployeeId())).thenReturn(employeeFull1);
        when(usersMapper.selectByPrimaryKey(employeeRequest.getUserId())).thenReturn(user);

        EmployeesResponse employeesResponse = employeeService.updateEmployee(employeeFull1.getEmployeeId(),
                employeeRequest);

        assertNotNull(employeesResponse);
        assertEquals(employeeRequest.getFirstName(), employeesResponse.getFirstName());
        assertEquals(employeeRequest.getLastName(), employeesResponse.getLastName());
    }

    @Test
    void testUpdateEmployee_NotFoundUserId() {
        when(employeesMapper.selectEmployeesWithDetailsById(employeeFull1.getEmployeeId())).thenReturn(employeeFull1);
        when(jobsMapper.selectByPrimaryKey(employeeRequest.getJobId())).thenReturn(job);
        when(departmentsMapper.selectByPrimaryKey(employeeRequest.getDepartmentId())).thenReturn(department);
        when(employeesMapper.selectEmployeesWithDetailsById(employeeFull2.getEmployeeId())).thenReturn(employeeFull2);

        DataNotFoundException exception = assertThrows(DataNotFoundException.class,
                () -> employeeService.updateEmployee(employeeFull1.getEmployeeId(), employeeRequest));

        assertEquals(String.format("User not found with user ID = %d", employeeFull1.getUserId()),
                exception.getMessage());
    }

    @Test
    void testUpdateEmployee_NotFoundManagerId() {
        when(employeesMapper.selectEmployeesWithDetailsById(employeeFull1.getEmployeeId())).thenReturn(employeeFull1);
        when(jobsMapper.selectByPrimaryKey(employeeRequest.getJobId())).thenReturn(job);
        when(departmentsMapper.selectByPrimaryKey(employeeRequest.getDepartmentId())).thenReturn(department);

        DataNotFoundException exception = assertThrows(DataNotFoundException.class,
                () -> employeeService.updateEmployee(employeeFull1.getEmployeeId(), employeeRequest));

        assertEquals(String.format("Manager not found with manager ID = %d", employeeFull1.getManagerId()),
                exception.getMessage());
    }

    @Test
    void testUpdateEmployee_NotFoundDeparmtentId() {
        when(employeesMapper.selectEmployeesWithDetailsById(employeeFull1.getEmployeeId())).thenReturn(employeeFull1);
        when(jobsMapper.selectByPrimaryKey(employeeRequest.getJobId())).thenReturn(job);

        DataNotFoundException exception = assertThrows(DataNotFoundException.class,
                () -> employeeService.updateEmployee(employeeFull1.getEmployeeId(), employeeRequest));

        assertEquals(String.format("Department not found with department ID = %d", employeeFull1.getDepartmentId()),
                exception.getMessage());
    }

    @Test
    void testUpdateEmployee_NotFoundJobId() {
        when(employeesMapper.selectEmployeesWithDetailsById(employeeFull1.getEmployeeId())).thenReturn(employeeFull1);

        DataNotFoundException exception = assertThrows(DataNotFoundException.class,
                () -> employeeService.updateEmployee(employeeFull1.getEmployeeId(), employeeRequest));

        assertEquals(String.format("Job not found with job ID = %d", employeeFull1.getJobId()),
                exception.getMessage());
    }

    @Test
    void testUpdateEmployee_NotFoundEmployeeId() {
        DataNotFoundException exception = assertThrows(DataNotFoundException.class,
                () -> employeeService.updateEmployee(employeeFull1.getEmployeeId(), employeeRequest));

        assertEquals(String.format("Employee not found with ID = %d", employeeFull1.getEmployeeId()),
                exception.getMessage());
    }

    @Test
    void testDeleteEmployee_throwDataNotFoundException() {
        Assertions.assertThrows(DataNotFoundException.class, () -> employeeService.deleteEmployee(100L));
    }

    @Test
    void testDeleteSoftEmployee_returnSuccess() throws DataNotFoundException {
        when(employeesMapper.selectEmployeesWithDetailsById(1L)).thenReturn(employeeFull1);
        when(employeesMapper.deleteSoftEmployee(1L)).thenReturn(1L);

        employeeService.deleteSoftEmployee(1L);
        verify(employeesMapper, times(1)).deleteSoftEmployee(1L);
    }

    @Test
    void testMappingEmployee_returnSuccess() throws MappingException {
        when(usersMapper.selectByPrimaryKey(employeeRequest.getUserId())).thenReturn(user);
        when(employeesMapper.selectEmployeesWithDetailsById(employeeFull1.getEmployeeId())).thenReturn(employeeFull1);
        when(employeesMapper.setUserIdForEmployee(employeeFull1.getEmployeeId(),
                employeeFull1.getUserId())).thenReturn(1L);

        employeeService.mappingEmployeeWithUser(employeeFull1.getEmployeeId(),
                employeeFull1.getUserId());
        verify(employeesMapper, times(1)).setUserIdForEmployee(1L, 5L);
    }

    @Test
    void testMappingEmployee_UserNotExist() {
        when(employeesMapper.selectEmployeesWithDetailsById(employeeFull1.getEmployeeId())).thenReturn(employeeFull1);

        DataNotFoundException exception = assertThrows(DataNotFoundException.class,
                () -> employeeService.mappingEmployeeWithUser(employeeFull1.getEmployeeId(),
                        employeeFull1.getUserId()));

        assertEquals(String.format("User not found with user ID = %d", employeeFull1.getUserId()),
                exception.getMessage());
    }

    @Test
    void testMappingEmployee_ExistingEmployeesAndUser() {
        Long userId = employeeFull1.getUserId();
        when(employeesMapper.selectEmployeesWithDetailsById(employeeFull1.getEmployeeId())).thenReturn(employeeFull1);
        when(usersMapper.selectByPrimaryKey(userId)).thenReturn(user);
        when(employeesMapper.selectByUserId(userId)).thenReturn(employeeFull1);

        DataNotFoundException exception = assertThrows(DataNotFoundException.class,
                () -> employeeService.mappingEmployeeWithUser(employeeFull1.getEmployeeId(),
                        employeeFull1.getUserId()));

        assertEquals(String.format("Employee is existed with user ID = %d", userId),
                exception.getMessage());
    }

    @Test
    void testMappingEmployee_MappingException()  {
        when(employeesMapper.selectEmployeesWithDetailsById(employeeFull1.getEmployeeId())).thenReturn(employeeFull1);
        when(usersMapper.selectByPrimaryKey(employeeFull1.getUserId())).thenReturn(user);

        MappingException exception = assertThrows(MappingException.class,
                () -> employeeService.mappingEmployeeWithUser(employeeFull1.getEmployeeId(),
                        employeeFull1.getUserId()));

        assertEquals("Cannot mapping employee with user",
                exception.getMessage());
    }

}
