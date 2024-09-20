package com.hr.management.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hr.management.model.JobHistoryFull;
import com.hr.management.model.JobHistoryKey;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobHistoryFullResponse extends JobHistoryKey {

    @JsonProperty("employee_id")
    private Long employeeId;

    // private JobHistoryKey jobHistoryKey;    

    @JsonProperty("employee_name")
    private String employeeName;


    @JsonProperty("start_date")
    private LocalDate startDate;

    @JsonProperty("end_date")
    private LocalDate endDate;

    @JsonProperty("job_id")
    private Long jobId;

    @JsonProperty("job_title")
    private String jobTitle;

    @JsonProperty("department_id")
    private Long departmentId;

    @JsonProperty("department_name")
    private String departmentName;

    public static JobHistoryFullResponse fromJobHistoryFull(JobHistoryFull jobHistoryFull){
        return JobHistoryFullResponse
                .builder()
                // .jobHistoryKey(jobHistoryFull.getJobHistoryKey())
                .employeeId(jobHistoryFull.getEmployeeId())
                .employeeName(jobHistoryFull.getEmployeeName())
                .startDate(jobHistoryFull.getStartDate())
                .endDate(jobHistoryFull.getEndDate())
                .jobId(jobHistoryFull.getJobId())
                .jobTitle(jobHistoryFull.getJobTitle())
                .departmentId(jobHistoryFull.getDepartmentId())
                .departmentName(jobHistoryFull.getDepartmentName())
                .build();
    }






}