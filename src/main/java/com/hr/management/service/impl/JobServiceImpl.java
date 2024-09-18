package com.hr.management.service.impl;

import com.hr.management.exception.DataNotFoundException;
import com.hr.management.mapper.JobsMapper;
import com.hr.management.model.Jobs;
import com.hr.management.model.JobsExample;
import com.hr.management.request.JobsRequest;
import com.hr.management.response.JobsResponse;
import com.hr.management.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

    @Autowired
    JobsMapper jobsMapper;
    JobsExample jobsExample;
    @Override
    public JobsResponse getJobById(Long jobId) {
        Jobs job = jobsMapper.selectByPrimaryKey(jobId);
        if(job == null){
            return null;
        }
        return JobsResponse.fromJobs(job);
    }

    @Override
    public List<JobsResponse> getAllJobs() {
        List<Jobs> jobsList = jobsMapper.selectByExample(jobsExample);
        return jobsList.stream()
                .map(JobsResponse::fromJobs).toList();
    }

    @Override
    public JobsResponse createJob(JobsRequest jobsRequest) {
        Jobs jobs = Jobs.fromJobsRequest(jobsRequest);
        jobsMapper.insertJobTitle(jobs);
        JobsResponse jobsResponse = JobsResponse.fromJobs(jobs);
        return jobsResponse;
    }

    @Override
    public JobsResponse updateJob(Long id, JobsRequest jobsRequest) throws Exception {
        Jobs exsitingJob = jobsMapper.selectByPrimaryKey(id);
        if(exsitingJob == null){
            throw new DataNotFoundException("Not found job with id " + id);
        }
        exsitingJob.setJobTitle(jobsRequest.getJobTitle());

        jobsMapper.updateByPrimaryKeySelective(exsitingJob);

        return JobsResponse.fromJobs(exsitingJob);
    }

    @Override
    public void deleteJob(Long id) throws DataNotFoundException {
        Jobs exsitingJob = jobsMapper.selectByPrimaryKey(id);
        if(exsitingJob == null){
            throw new DataNotFoundException("Not found job with id " + id);
        }
        jobsMapper.deleteByPrimaryKey(id);
    }


}
