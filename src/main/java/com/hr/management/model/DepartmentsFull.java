package com.hr.management.model;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
@Builder
public class DepartmentsFull {
    private Long departmentId;
    private String departmentName;
    private Long managerId;
    private String managerName;
    private List<Employees> employees;
}
