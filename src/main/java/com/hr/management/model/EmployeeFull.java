package com.hr.management.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Data
public class EmployeeFull {

    private Long employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private LocalDate hireDate;
    private float salary;
    private Long managerId;
    private Long departmentId;
    private Long jobId;
    private String managerName;
    private Users user;
    private Departments department;
    private Jobs job;
}
