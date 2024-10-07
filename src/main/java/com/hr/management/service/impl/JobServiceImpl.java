package com.hr.management.service.impl;

import com.hr.management.exception.DataNotFoundException;
import com.hr.management.exception.JobHasAssociatedEmployeeException;
import com.hr.management.mapper.EmployeesMapper;
import com.hr.management.mapper.JobsMapper;
import com.hr.management.model.Jobs;
import com.hr.management.model.JobsExample;
import com.hr.management.request.JobsRequest;
import com.hr.management.response.JobsResponse;
import com.hr.management.service.JobService;
import lombok.RequiredArgsConstructor;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    private final JobsMapper jobsMapper;
    private final EmployeesMapper employeesMapper;
    JobsExample jobsExample;
    @Override
    public JobsResponse getJobById(Long jobId) {
        Jobs job = jobsMapper.selectByPrimaryKey(jobId);
        if(job == null){
            ;throw new DataNotFoundException(String.format(
                    "Job not found with ID = %d", jobId));
        }
        return JobsResponse.fromJobs(job);
    }

    @Override
    public List<JobsResponse> getAllJobs() {
        List<Jobs> jobsList = jobsMapper.selectByExample();
        return jobsList.stream()
                .map(JobsResponse::fromJobs).toList();
    }

    @Override
    public JobsResponse createJob(JobsRequest jobsRequest) {
        Jobs jobs = Jobs.fromJobsRequest(jobsRequest);
        if(jobsMapper.selectJobByJobTitle(jobsRequest.getJobTitle()).size() > 0) {
            throw new DataIntegrityViolationException("Job title is already existed");
        }
        jobsMapper.insertJobTitle(jobs);
        JobsResponse jobsResponse = JobsResponse.fromJobs(jobs);
        return jobsResponse;
    }

    @Override
    public JobsResponse updateJob(Long id, JobsRequest jobsRequest)  {
        Jobs exsitingJob = jobsMapper.selectByPrimaryKey(id);
        if(exsitingJob == null){
            throw new DataNotFoundException("Not found job with id " + id);
        }
        exsitingJob.setJobTitle(jobsRequest.getJobTitle());

        jobsMapper.updateByPrimaryKeySelective(exsitingJob);

        return JobsResponse.fromJobs(exsitingJob);
    }

    @Override
    public void deleteJob(Long id) throws JobHasAssociatedEmployeeException {
        Jobs exsitingJob = jobsMapper.selectByPrimaryKey(id);
        if(exsitingJob == null){
            throw new DataNotFoundException("Not found job with id " + id);
        }
        Long jobCount = employeesMapper.isJobExisting(id);
        if(jobCount != 0){
            throw new JobHasAssociatedEmployeeException("Cannot delete job with id " + id + " because it is associated with employees.");
        }
        jobsMapper.deleteByPrimaryKey(id);
    }


}
