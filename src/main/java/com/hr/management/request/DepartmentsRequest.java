package com.hr.management.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hr.management.model.DepartmentsFull;
import com.hr.management.response.DepartmentsResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepartmentsRequest {

    @JsonProperty("department_name")
    @NotEmpty(message = "Department name is required")
    private String departmentName;

    @JsonProperty("manager_id")
    @NotNull(message = "manager ID is required")
    private Long managerId;

    public static DepartmentsRequest fromDepartmentFull(DepartmentsFull departmentsFull){
        return DepartmentsRequest
                .builder()
                .departmentName(departmentsFull.getDepartmentName())
                .managerId(departmentsFull.getManagerId())
                .build();
    }
}
