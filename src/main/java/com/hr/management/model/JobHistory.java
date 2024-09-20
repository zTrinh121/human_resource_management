package com.hr.management.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hr.management.request.JobHistoryRequest;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;
import java.util.Date;

@Builder
public class JobHistory extends JobHistoryKey {
    private JobHistoryKey jobHistoryKey;
    private LocalDate endDate;
    private Long jobId;
    private Long departmentId;
    public LocalDate getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
    public Long getJobId() {
        return jobId;
    }
    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }
    public Long getDepartmentId() {
        return departmentId;
    }
    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public static JobHistory fromJobHistoryRequest(JobHistoryRequest jobHistoryRequest){
        return JobHistory.builder()
                
                .endDate(jobHistoryRequest.getEndDate())
                .jobId(jobHistoryRequest.getJobId())
                .departmentId(jobHistoryRequest.getDepartmentId())
                .build();
    }
}