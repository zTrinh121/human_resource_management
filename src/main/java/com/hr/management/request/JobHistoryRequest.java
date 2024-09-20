package com.hr.management.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hr.management.model.JobHistoryKey;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobHistoryRequest {
//
    @JsonProperty("employee_id")
    @NotNull(message = "Employee ID is required")
    private Long employeeId;

    @JsonProperty("start_date")
    @NotNull(message = "Start Date is required")
    private LocalDate startDate;

    @JsonProperty("end_date")
    @NotNull(message = "End Date is required")
    private LocalDate endDate;

    @JsonProperty("job_id")
    @NotNull(message = "Job ID is required")
    private Long jobId;

    @JsonProperty("department_id")
    @NotNull(message = "Deparment ID is required")
    private Long departmentId;


}