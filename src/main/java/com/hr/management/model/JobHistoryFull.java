package com.hr.management.model;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
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


}