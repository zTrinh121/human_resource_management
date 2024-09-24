package com.hr.management.model;

import com.hr.management.request.EmployeesRequest;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employees {
    private Long employeeId;
    private String firstName;
    private String lastName;
    private String email;

    private String phoneNumber;
    private LocalDate dateOfBirth;
    private LocalDate hireDate;
    private Long jobId;
    private Long userId;
    private float salary;

    private Long managerId;
    private Long departmentId;

    public static Employees fromEmployeeRequest(EmployeesRequest employeesRequest){
        return Employees
                .builder()
                .firstName(employeesRequest.getFirstName())
                .lastName(employeesRequest.getLastName())
                .email(employeesRequest.getEmail())
                .phoneNumber(employeesRequest.getPhoneNumber())
                .dateOfBirth(employeesRequest.getDateOfBirth())
                .hireDate(employeesRequest.getHireDate())
                .jobId(employeesRequest.getJobId())
                .salary(employeesRequest.getSalary())
                .managerId(employeesRequest.getManagerId())
                .departmentId(employeesRequest.getDepartmentId())
                .build();
    }
}