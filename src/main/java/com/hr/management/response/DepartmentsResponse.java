package com.hr.management.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hr.management.model.Departments;
import com.hr.management.model.DepartmentsFull;
import com.hr.management.request.DepartmentsRequest;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepartmentsResponse {
    @JsonProperty("department_id")
    private Long departmentId;

    @JsonProperty("department_name")
    private String departmentName;

    @JsonProperty("manager_id")
    private Long managerId;

    @JsonProperty("manager_name")
    private String managerName;

    public static DepartmentsResponse fromDepartmentRequest(DepartmentsRequest departmentsRequest){
        return DepartmentsResponse
                .builder()
                .departmentName(departmentsRequest.getDepartmentName())
                .managerId(departmentsRequest.getManagerId())
                .build();
    }

    public static DepartmentsResponse fromDepartment(Departments departments){
        return DepartmentsResponse
                .builder()
                .departmentId(departments.getDepartmentId())
                .departmentName(departments.getDepartmentName())
                .managerId(departments.getManagerId())
                .build();
    }

    public static DepartmentsResponse fromDepartmentFull(DepartmentsFull departments){
        return DepartmentsResponse
                .builder()
                .departmentId(departments.getDepartmentId())
                .departmentName(departments.getDepartmentName())
                .managerId(departments.getManagerId())
                .managerName(departments.getManagerName())
                .build();
    }


}
