package com.hr.management.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hr.management.model.Jobs;
import com.hr.management.request.JobsRequest;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobsResponse {

    @JsonProperty("job_id")

    private Long jobId;
    @JsonProperty("job_title")
    private String jobTitle;

    public static JobsResponse fromJobs(Jobs jobs){
        return JobsResponse
                .builder()
                .jobId(jobs.getJobId())
                .jobTitle(jobs.getJobTitle())
                .build();
    }

    public static JobsResponse fromJobsRequest(JobsRequest jobsRequest){
        return JobsResponse
                .builder()
                .jobTitle(jobsRequest.getJobTitle())
                .build();
    }
}
