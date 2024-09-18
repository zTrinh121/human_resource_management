package com.hr.management.service;

import com.hr.management.model.Jobs;
import com.hr.management.response.JobsResponse;

import java.util.List;

public interface JobService {

    JobsResponse getJobById(Long id);

}
