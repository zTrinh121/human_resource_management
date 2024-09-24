package com.hr.management.model;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

import com.hr.management.request.JobHistoryRequest;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class JobHistoryFull  {
     private Long employeeId;
    private JobHistoryKey jobHistoryKey;
    private String employeeName;
     private LocalDate startDate;
    private LocalDate endDate;
    private Long jobId;
    private String jobTitle;
    private Long departmentId;
    private String departmentName;


     public static JobHistoryFull fromJobHistoryRequest(JobHistoryRequest jobHistoryRequest){
        return JobHistoryFull
                .builder()
                // .jobHistoryKey(jobHistoryFull.getJobHistoryKey())
                .employeeId(jobHistoryRequest.getEmployeeId())
                .startDate(jobHistoryRequest.getStartDate())
                .endDate(jobHistoryRequest.getEndDate())
                .jobId(jobHistoryRequest.getJobId())
                .departmentId(jobHistoryRequest.getDepartmentId())
                .build();
    }


}