package com.hr.management.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hr.management.model.*;
import com.hr.management.request.EmployeesRequest;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class EmployeesResponse {
    @JsonProperty("employee_id")
    private Long employeeId;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    private String email;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("date_of_birth")
    private LocalDate dateOfBirth;

    @JsonProperty("hire_date")
    private LocalDate hireDate;

    private float salary;

    @JsonProperty("manager_id")
    private Long managerId;

    @JsonProperty("department_id")
    private Long departmentId;

    @JsonProperty("job_id")
    private Long jobId;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("manager_name")
    private String managerName;

    @JsonProperty("job_title")
    private String jobTitle;

    @JsonProperty("department_name")
    private String departmentName;

    @JsonProperty("employee_status")
    private String employeeStatus;

//    private Users user;
//    private Departments department;
//    private Jobs job;

    public static EmployeesResponse fromEmployeeRequest(EmployeesRequest employeesRequest){
        return EmployeesResponse
                .builder()
                .firstName(employeesRequest.getFirstName())
                .lastName(employeesRequest.getLastName())
                .email(employeesRequest.getEmail())
                .phoneNumber(employeesRequest.getPhoneNumber())
                .dateOfBirth(employeesRequest.getDateOfBirth())
                .hireDate(employeesRequest.getHireDate())
                .salary(employeesRequest.getSalary())
                .managerId(employeesRequest.getManagerId())
                .jobId(employeesRequest.getJobId())
                .userId(employeesRequest.getUserId())
                .departmentId(employeesRequest.getDepartmentId())
                .employeeStatus(employeesRequest.getEmployeeStatus())
                .build();
    }

    public static EmployeesResponse fromEmployee(Employees employees){
        return EmployeesResponse
                .builder()
                .employeeId(employees.getEmployeeId())
                .firstName(employees.getFirstName())
                .lastName(employees.getLastName())
                .email(employees.getEmail())
                .phoneNumber(employees.getPhoneNumber())
                .dateOfBirth(employees.getDateOfBirth())
                .hireDate(employees.getHireDate())
                .salary(employees.getSalary())
                .managerId(employees.getManagerId())
                .jobId(employees.getJobId())
                .userId(employees.getUserId())
                .departmentId(employees.getDepartmentId())
                .employeeStatus(employees.getEmployeeStatus())
                .build();
    }

    public static EmployeesResponse fromEmployeeFull(EmployeeFull employees){
        return EmployeesResponse
                .builder()
                .employeeId(employees.getEmployeeId())
                .firstName(employees.getFirstName())
                .lastName(employees.getLastName())
                .email(employees.getEmail())
                .phoneNumber(employees.getPhoneNumber())
                .dateOfBirth(employees.getDateOfBirth())
                .hireDate(employees.getHireDate())
                .salary(employees.getSalary())
                .managerId(employees.getManagerId())
                .jobId(employees.getJobId())
                .userId(employees.getUserId())
                .departmentId(employees.getDepartmentId())
                .managerName(employees.getManagerName())
                .jobTitle(employees.getJobTitle())
                .departmentName(employees.getDepartmentName())
                .employeeStatus(employees.getEmployeeStatus())
                .build();
    }



}
