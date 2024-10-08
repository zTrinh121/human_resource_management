package com.hr.management.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeFull {

    private Long employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private LocalDate hireDate;
    private float salary;
    private String employeeStatus;
    private Long managerId;
    private Long departmentId;
    private Long jobId;
    private Long userId;
    private String managerName;
    private String jobTitle;
    private String departmentName;
}
