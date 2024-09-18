package com.hr.management.service;

import com.hr.management.exception.DataNotFoundException;
import com.hr.management.request.JobsRequest;
import com.hr.management.response.JobsResponse;

import java.util.List;

public interface JobService {

    JobsResponse getJobById(Long id);

    List<JobsResponse> getAllJobs();

    JobsResponse createJob(JobsRequest jobsRequest);

    JobsResponse updateJob(Long id, JobsRequest jobsRequest) throws Exception;

    void deleteJob(Long id) throws DataNotFoundException;

}
