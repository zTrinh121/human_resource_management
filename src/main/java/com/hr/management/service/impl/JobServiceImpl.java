package com.hr.management.service.impl;

import com.hr.management.mapper.JobsMapper;
import com.hr.management.model.Jobs;
import com.hr.management.model.JobsExample;
import com.hr.management.response.JobsResponse;
import com.hr.management.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    JobsMapper jobsMapper;
    JobsExample jobsExample;
    @Override
    public JobsResponse getJobById(Long jobId) {
        return JobsResponse.fromJobs(jobsMapper.selectByPrimaryKey(jobId));
    }
}
