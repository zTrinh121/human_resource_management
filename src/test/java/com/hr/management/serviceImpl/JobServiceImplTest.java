package com.hr.management.serviceImpl;

import com.hr.management.exception.JobHasAssociatedEmployeeException;
import com.hr.management.mapper.EmployeesMapper;
import com.hr.management.mapper.JobsMapper;
import com.hr.management.model.Jobs;
import com.hr.management.request.JobsRequest;
import com.hr.management.response.JobsResponse;
import com.hr.management.service.impl.JobServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class JobServiceImplTest {

    @Mock
    private JobsMapper jobsMapper;
    @Mock
    private EmployeesMapper employeesMapper;
    private JobServiceImpl jobService;
    AutoCloseable autoCloseable;
    Jobs jobs;
    JobsRequest jobsRequest;
    @BeforeEach
    void setUp(){
        autoCloseable = MockitoAnnotations.openMocks(this);
        jobService = new JobServiceImpl(jobsMapper, employeesMapper);
        jobs = new Jobs(10L, "Manager");
        jobsRequest = new JobsRequest("Manager Intern");
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void testGetJobById_returnJobResponse(){
        mock(Jobs.class);
        mock(JobsMapper.class);

        when(jobsMapper.selectByPrimaryKey(jobs.getJobId())).thenReturn(jobs);
        assertThat(jobService.getJobById(jobs.getJobId()).getJobTitle())
                .isEqualTo(jobs.getJobTitle());
    }

    @Test
    void testGetAllJobs() {
        Jobs job1 = new Jobs(1L, "Manager");
        Jobs job2 = new Jobs(2L, "Engineer");
        List<Jobs> mockJobsList = Arrays.asList(job1, job2);

        when(jobsMapper.selectByExample()).thenReturn(mockJobsList);

        List<JobsResponse> result = jobService.getAllJobs();

        assertEquals(2, result.size());
        assertEquals(JobsResponse.fromJobs(job1).getJobTitle(), result.get(0).getJobTitle());
        assertEquals(JobsResponse.fromJobs(job2).getJobTitle(), result.get(1).getJobTitle());
    }


    @Test
    void testCreateJob_returnJobResponse() {
        mock(Jobs.class);
        mock(JobsMapper.class);

        when(jobsMapper.insertJobTitle(jobs)).thenReturn(1);
        assertThat(jobService.createJob(jobsRequest).getJobTitle())
                .isEqualTo(jobsRequest.getJobTitle());
    }

    @Test
    void testUpdateJob_returnJobResponse() throws Exception {
        mock(Jobs.class);
        mock(JobsMapper.class);

        jobs.setJobTitle(jobs.getJobTitle());
        when(jobsMapper.selectByPrimaryKey(jobs.getJobId())).thenReturn(jobs);
        when(jobsMapper.updateByPrimaryKey(jobs))
                .thenReturn(1);
        assertThat(jobService.updateJob(jobs.getJobId(), jobsRequest).getJobTitle())
                .isEqualTo(jobsRequest.getJobTitle());
    }

    @Test
    void testDeleteJob_returnVoid() throws JobHasAssociatedEmployeeException {
        mock(Jobs.class);
        mock(JobsMapper.class);
        mock(EmployeesMapper.class);

        when(jobsMapper.selectByPrimaryKey(jobs.getJobId())).thenReturn(jobs);
        doReturn(1).when(jobsMapper).deleteByPrimaryKey(jobs.getJobId());

        jobService.deleteJob(jobs.getJobId());
        verify(jobsMapper, times(1))
                .deleteByPrimaryKey(jobs.getJobId());

    }
}
