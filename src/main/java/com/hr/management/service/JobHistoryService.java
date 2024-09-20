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

    JobHistoryFullResponse getJobHistorByIdAndDate(Long employeeId, LocalDate startDate, LocalDate endDate) throws DataNotFoundException;
    List<JobHistoryFullResponse> getAllJobHistory();

    JobHistoryFullResponse createJobHistory(JobHistoryRequest jobHistoryRequest) throws DataNotFoundException;

    JobHistoryFullResponse updateJobHistory(Long employeeId, LocalDate startDate, JobHistoryRequest jobHistoryRequest) throws Exception;

    void deleteJobHistory(Long employeeId, LocalDate startDate) throws DataNotFoundException;

}
