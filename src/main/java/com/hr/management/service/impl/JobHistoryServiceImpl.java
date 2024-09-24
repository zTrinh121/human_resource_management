package com.hr.management.service.impl;

import com.hr.management.exception.DataNotFoundException;
import com.hr.management.mapper.DepartmentsMapper;
import com.hr.management.mapper.EmployeesMapper;
import com.hr.management.mapper.JobHistoryMapper;
import com.hr.management.mapper.JobsMapper;
import com.hr.management.model.DepartmentsFull;
import com.hr.management.model.EmployeeFull;
import com.hr.management.model.JobHistoryExample;
import com.hr.management.model.JobHistoryFull;
import com.hr.management.model.Jobs;
import com.hr.management.request.JobHistoryRequest;
import com.hr.management.request.JobsRequest;
import com.hr.management.response.JobHistoryFullResponse;
import com.hr.management.service.JobHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class JobHistoryServiceImpl implements JobHistoryService {

    @Autowired
    JobHistoryMapper jobHistoryMapper;

    @Autowired
    EmployeesMapper employeesMapper;

    @Autowired
    JobsMapper jobsMapper;

    @Autowired
    DepartmentsMapper departmentsMapper;

    JobHistoryExample jobHistoryExample;
    @Override
    public List<JobHistoryFullResponse> getJobHistorByEmployeeId(Long id) {
        List<JobHistoryFull> jobHistoryFulls = jobHistoryMapper.getJobHistoryFullByEmployeeId(id);
        List<JobHistoryFullResponse> jobHistoryFullResponses = jobHistoryFulls
                .stream()
                .map(JobHistoryFullResponse::fromJobHistoryFull)
                .toList();
        return jobHistoryFullResponses;
    }

    @Override
        public JobHistoryFullResponse getJobHistorByIdAndDate(Long employeeId, LocalDate startDate, LocalDate endDate) throws DataNotFoundException {
        JobHistoryFull jobHistoryFull = jobHistoryMapper.getJobHistoryFullByEmployeeIdDate(
                employeeId,
                startDate,
                endDate);

        if (jobHistoryFull == null) {
            throw new DataNotFoundException("Data not found for given id and date");
        }
        return JobHistoryFullResponse.fromJobHistoryFull(jobHistoryFull);
    }


    @Override
    public List<JobHistoryFullResponse> getAllJobHistory() {
        List<JobHistoryFull> jobHistoryFulls = jobHistoryMapper.getJobHistoryDetail();
        List<JobHistoryFullResponse> jobHistoryFullResponses = jobHistoryFulls
                .stream()
                .map(JobHistoryFullResponse::fromJobHistoryFull)
                .toList();
        return jobHistoryFullResponses;
    }

    @Override
    public JobHistoryFullResponse createJobHistory(JobHistoryRequest jobHistoryRequest) throws DataNotFoundException {
        //Check if employee exists or not
        EmployeeFull existingEmployeeFull = employeesMapper.selectEmployeesWithDetailsById(jobHistoryRequest.getEmployeeId());
        if(existingEmployeeFull == null){
            throw new DataNotFoundException(String.format("Employee not found: %d", jobHistoryRequest.getEmployeeId()));
        }

        //Check job exists or not
        Jobs job = jobsMapper.selectByPrimaryKey(jobHistoryRequest.getJobId());
        if(job == null){
            throw new DataNotFoundException(String.format("Job not found: %d", jobHistoryRequest.getJobId()));
        }

        //Check department exists or not
        DepartmentsFull department = departmentsMapper.getDepartmentsWithManagerNameById(jobHistoryRequest.getDepartmentId());
        if (department == null) {
            throw new DataNotFoundException(String.format("Department not found: %d", jobHistoryRequest.getDepartmentId()));
        }

        //Check start date before end date
        if(!jobHistoryRequest.getStartDate().isBefore(jobHistoryRequest.getEndDate())){
            throw new DataNotFoundException("Start date must before end date");
        }

        jobHistoryMapper.insert(jobHistoryRequest);

        JobHistoryFullResponse jobHistoryFullResponse = getJobHistorByIdAndDate(jobHistoryRequest.getEmployeeId(),
                jobHistoryRequest.getStartDate(), jobHistoryRequest.getEndDate());

        return jobHistoryFullResponse;
    }

    @Override
    public JobHistoryFullResponse updateJobHistory(Long employeeId, LocalDate startDate, JobHistoryRequest jobHistoryRequest) throws Exception {
        //Check if job history exists or not
        JobHistoryFull jobHistory = jobHistoryMapper.getJobHistoryFullByEmployeeIdStartDate(employeeId, startDate);
        if (jobHistory == null) {
            throw new DataNotFoundException("Job history not found");
        }

         //Check if employee exists or not
         EmployeeFull existingEmployeeFull = employeesMapper.selectEmployeesWithDetailsById(jobHistoryRequest.getEmployeeId());
         if(existingEmployeeFull == null){
             throw new DataNotFoundException(String.format("Employee not found: %d", jobHistoryRequest.getEmployeeId()));
         }
 
         //Check job exists or not
         Jobs job = jobsMapper.selectByPrimaryKey(jobHistoryRequest.getJobId());
         if(job == null){
             throw new DataNotFoundException(String.format("Job not found: %d", jobHistoryRequest.getJobId()));
         }
 
         //Check department exists or not
         DepartmentsFull department = departmentsMapper.getDepartmentsWithManagerNameById(jobHistoryRequest.getDepartmentId());
         if (department == null) {
             throw new DataNotFoundException(String.format("Department not found: %d", jobHistoryRequest.getDepartmentId()));
         }
 
         //Check start date before end date
         if(!jobHistoryRequest.getStartDate().isBefore(jobHistoryRequest.getEndDate())){
             throw new DataNotFoundException("Start date must before end date");
         }

        jobHistoryMapper.updateByPrimaryKey(jobHistory);
        JobHistoryFullResponse jobHistoryFullResponse = JobHistoryFullResponse.fromJobHistoryFull(
            jobHistoryMapper.getJobHistoryFullByEmployeeIdStartDate(jobHistoryRequest.getEmployeeId(),
        jobHistoryRequest.getStartDate()));

        return jobHistoryFullResponse;
    }

    @Override
    public void deleteJobHistory(Long employeeId, LocalDate startDate) throws DataNotFoundException {
        int row = jobHistoryMapper.deleteByPrimaryKey(employeeId, startDate);
        System.out.println(row);
    }
}
