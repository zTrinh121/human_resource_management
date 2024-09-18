package com.hr.management.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class DepartmentFull {
    private Long departmentId;

    @NotEmpty(message = "Department name is required")
    private String departmentName;

    @NotEmpty(message = "manager ID is required")
    private Long managerId;

    private String managerName;
}
