package com.hr.management.model;

import com.hr.management.request.JobsRequest;
import com.hr.management.response.JobsResponse;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Jobs {
    @NotNull(message = "job id cannot be null")
    private Long jobId;
    private String jobTitle;
    public Long getJobId() {
        return jobId;
    }
    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }
    public String getJobTitle() {
        return jobTitle;
    }
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle == null ? null : jobTitle.trim();
    }

    public static Jobs fromJobsRequest(JobsRequest jobsRequest){
        return Jobs
                .builder()
                .jobTitle(jobsRequest.getJobTitle())
                .build();
    }

}