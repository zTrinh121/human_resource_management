package serviceImpl;

import com.hr.management.exception.DataNotFoundException;
import com.hr.management.mapper.JobsMapper;
import com.hr.management.model.Jobs;
import com.hr.management.model.JobsExample;
import com.hr.management.request.JobsRequest;
import com.hr.management.response.JobsResponse;
import com.hr.management.service.impl.JobServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class JobServiceImplTest {

    @Mock
    private JobsMapper jobsMapper;
    private JobServiceImpl jobService;
    AutoCloseable autoCloseable;
    Jobs jobs;
    JobsRequest jobsRequest;
    @BeforeEach
    void setUp(){
        autoCloseable = MockitoAnnotations.openMocks(this);
        jobService = new JobServiceImpl(jobsMapper);
        jobs = new Jobs(1L, "Manager");
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
    void testGetAllJob_returnlistJobResponse(){
        System.out.println("Job list from mapper: " + jobService.getAllJobs());
        when(jobsMapper.selectByExample(any(JobsExample.class)))
                .thenReturn(Collections.singletonList(jobs));
        List<JobsResponse> jobsResponses = jobService.getAllJobs();
        System.out.println(jobsResponses.size());
        assertFalse(jobsResponses.isEmpty());
        assertThat(jobsResponses.get(0).getJobTitle())
                .isEqualTo(jobs.getJobTitle());
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
    void testDeleteJob_returnVoid() throws DataNotFoundException {
        mock(Jobs.class);
        mock(JobsMapper.class);

        when(jobsMapper.selectByPrimaryKey(jobs.getJobId())).thenReturn(jobs);
        when(jobsMapper.deleteByPrimaryKey(jobs.getJobId()))
                .thenReturn(1);
        jobService.deleteJob(jobs.getJobId());
        verify(jobsMapper, times(1))
                .deleteByPrimaryKey(jobs.getJobId());

    }
}
