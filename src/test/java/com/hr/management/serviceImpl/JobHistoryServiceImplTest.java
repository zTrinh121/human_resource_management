package com.hr.management.serviceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.hr.management.exception.DataNotFoundException;
import com.hr.management.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hr.management.mapper.DepartmentsMapper;
import com.hr.management.mapper.EmployeesMapper;
import com.hr.management.mapper.JobHistoryMapper;
import com.hr.management.mapper.JobsMapper;
import com.hr.management.request.JobHistoryRequest;
import com.hr.management.response.JobHistoryFullResponse;
import com.hr.management.service.impl.JobHistoryServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class JobHistoryServiceImplTest {

    @Mock
    private JobHistoryMapper jobHistoryMapper;
    @Mock
    private EmployeesMapper employeesMapper;
    @Mock
    private DepartmentsMapper departmentsMapper;
    @Mock
    private JobsMapper jobsMapper;

    @InjectMocks
    private JobHistoryServiceImpl jobHistoryService;

    private JobHistoryRequest jobHistoryRequest1;
    private JobHistoryRequest jobHistoryRequest2;
    private EmployeeFull employees;
    private Jobs jobs;
    private DepartmentsFull department;

    @BeforeEach
    void setUp() {
        jobHistoryRequest1 = new JobHistoryRequest(
            1L, 
            LocalDate.of(2020, 5, 1),
            LocalDate.of(2023, 5, 1),
            1L,
            2L
        );
        jobHistoryRequest2 = new JobHistoryRequest(
            1L, 
            LocalDate.of(2019, 1, 1),
            LocalDate.of(2022, 1, 1),
            1L,
            2L
            );
        
        employees = new EmployeeFull(
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
            1L,
            5L,
            "Jane Smith",
            "Software Engineer",
            "Engineering"
    );
        jobs = new Jobs(1L, "Manager");
        department = new DepartmentsFull(2L, "Engineering", 2L, "Jane Doe");
    }

    @Test
    void testGetJobHistoryByEmployeeId_returnJobHistory() {
        List<JobHistoryFull> jobs = new ArrayList<>();
        jobs.add(JobHistoryFull.fromJobHistoryRequest(jobHistoryRequest1));
        jobs.add(JobHistoryFull.fromJobHistoryRequest(jobHistoryRequest2));
        when(jobHistoryMapper.getJobHistoryFullByEmployeeId(jobHistoryRequest1.getEmployeeId()))
        .thenReturn(jobs);

        List<JobHistoryFullResponse> jobHistoryFullResponses = jobHistoryService.getJobHistorByEmployeeId(jobHistoryRequest1.getEmployeeId());

        assertEquals(2, jobHistoryFullResponses.size());
        assertEquals(jobHistoryRequest1.getEmployeeId(), jobHistoryFullResponses.get(0).getEmployeeId());
    }

    @Test
    void testGetJobHistorByIdAndDate_returnJobHistoryFullResponse() throws DataNotFoundException {
    
        when(jobHistoryMapper.getJobHistoryFullByEmployeeIdDate(jobHistoryRequest1.getEmployeeId(), 
        jobHistoryRequest1.getStartDate(),
        jobHistoryRequest1.getEndDate()))
        .thenReturn(JobHistoryFull.fromJobHistoryRequest(jobHistoryRequest1));

        JobHistoryFullResponse jobHistoryFullResponse = jobHistoryService.getJobHistorByIdAndDate(
                jobHistoryRequest1.getEmployeeId(),
                jobHistoryRequest1.getStartDate(),
                jobHistoryRequest1.getEndDate());

        assertEquals(jobHistoryRequest1.getEmployeeId(), jobHistoryFullResponse.getEmployeeId());
    }

    @Test
    void testGetAllJobHistory_returnJobHistoryFullResponse(){
        List<JobHistoryFull> jobs = new ArrayList<>();
        jobs.add(JobHistoryFull.fromJobHistoryRequest(jobHistoryRequest1));
        jobs.add(JobHistoryFull.fromJobHistoryRequest(jobHistoryRequest2));
        when(jobHistoryMapper.getJobHistoryDetail()).thenReturn(jobs);

        List<JobHistoryFullResponse> jobHistoryFullResponses = jobHistoryService.getAllJobHistory();

        assertEquals(2, jobHistoryFullResponses.size());
        assertEquals(jobHistoryRequest1.getEmployeeId(), jobHistoryFullResponses.get(0).getEmployeeId());
    }

    @Test
    void testCreatJobHistory_returnJobHistoryFullResponse() throws DataNotFoundException {
        when(employeesMapper.selectEmployeesWithDetailsById(employees.getEmployeeId())).thenReturn(employees);
        when(jobsMapper.selectByPrimaryKey(jobHistoryRequest1.getJobId())).thenReturn(jobs);
        when(departmentsMapper.getDepartmentsWithManagerNameById(department.getDepartmentId())).thenReturn(department);
        when(jobHistoryMapper.insert(jobHistoryRequest1)).thenReturn(1);
        when(jobHistoryMapper.getJobHistoryFullByEmployeeIdDate(jobHistoryRequest1.getEmployeeId(),
                jobHistoryRequest1.getStartDate(),
                jobHistoryRequest1.getEndDate()))
                .thenReturn(JobHistoryFull.fromJobHistoryRequest(jobHistoryRequest1));

        JobHistoryFullResponse jobHistoryFullResponse = jobHistoryService.createJobHistory(jobHistoryRequest1);
        assertEquals(jobHistoryRequest1.getEmployeeId(), jobHistoryFullResponse.getEmployeeId());
    }

    @Test
    void testUpdateJobHistory_returnJobHistoryFullResponse() throws Exception {
        System.out.println(jobHistoryRequest1);
        when(jobHistoryMapper.getJobHistoryFullByEmployeeIdStartDate(any(Long.class), any(LocalDate.class))).thenReturn(JobHistoryFull.fromJobHistoryRequest(jobHistoryRequest1));
        when(jobsMapper.selectByPrimaryKey(jobHistoryRequest1.getJobId())).thenReturn(jobs);
        when(departmentsMapper.getDepartmentsWithManagerNameById(department.getDepartmentId())).thenReturn(department);
        when(employeesMapper.selectEmployeesWithDetailsById(employees.getEmployeeId())).thenReturn(employees);
        when(jobHistoryMapper.updateByPrimaryKey(JobHistoryFull.fromJobHistoryRequest(jobHistoryRequest1))).thenReturn(1);

        JobHistoryFullResponse jobHistoryFullResponse = jobHistoryService.updateJobHistory(
                jobHistoryRequest1.getEmployeeId(),
                jobHistoryRequest1.getStartDate(),
                jobHistoryRequest1);

        assertEquals(jobHistoryRequest1.getEmployeeId(), jobHistoryFullResponse.getEmployeeId());
    }

    @Test
    void testDeleteJobHistory_returnVoid() throws Exception {
        when(jobHistoryMapper.deleteByPrimaryKey(
                jobHistoryRequest1.getEmployeeId(),
                jobHistoryRequest1.getStartDate()))
                .thenReturn(1);

        jobHistoryService.deleteJobHistory(jobHistoryRequest1.getEmployeeId(), jobHistoryRequest1.getStartDate());
         Mockito.verify(jobHistoryMapper, Mockito.times(1)).deleteByPrimaryKey(jobHistoryRequest1.getEmployeeId(), jobHistoryRequest1.getStartDate());
    }


}
