package com.hr.management.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeesRequest {

    @NotEmpty(message = "First name is required")
    @JsonProperty("first_name")
    private String firstName;

    @NotEmpty(message = "Last name is required")
    @JsonProperty("last_name")
    private String lastName;

    @NotEmpty(message = "Email is required")
    private String email;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("date_of_birth")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @JsonProperty("hire_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate hireDate;

    @Min(value = 1, message = "Minimum salary is greater than 0")
    private float salary;

    @JsonProperty("manager_id")
    private Long managerId;

    @JsonProperty("department_id")
    private Long departmentId;

    @JsonProperty("job_id")
    @NotNull(message = "Job ID is required")
    private Long jobId;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("employee_status")
    @NotEmpty(message = "Employee status is required")
    private String employeeStatus;
}
