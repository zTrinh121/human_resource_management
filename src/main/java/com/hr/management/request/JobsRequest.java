package com.hr.management.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobsRequest {

    @NotEmpty(message = "Job title is required")
    @JsonProperty("job_title")
    private String jobTitle;
}
