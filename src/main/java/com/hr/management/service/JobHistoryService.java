package com.hr.management.service;

import com.hr.management.exception.DataNotFoundException;
import com.hr.management.model.JobHistoryKey;
import com.hr.management.request.JobHistoryRequest;
import com.hr.management.request.JobsRequest;
import com.hr.management.response.JobHistoryFullResponse;
import com.hr.management.response.JobsResponse;

import java.time.LocalDate;
import java.util.List;

public interface JobHistoryService {

    List<JobHistoryFullResponse> getJobHistorByEmployeeId(Long id);

    JobHistoryFullResponse getJobHistorByIdAndDate(Long employeeId, LocalDate startDate, LocalDate endDate) ;
    List<JobHistoryFullResponse> getAllJobHistory();

    JobHistoryFullResponse createJobHistory(JobHistoryRequest jobHistoryRequest) ;

    JobHistoryFullResponse updateJobHistory(Long employeeId, LocalDate startDate, JobHistoryRequest jobHistoryRequest) ;

    void deleteJobHistory(Long employeeId, LocalDate startDate) ;

}
