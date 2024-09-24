package com.hr.management.model;


import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentsFull {
    private Long departmentId;
    private String departmentName;
    private Long managerId;
    private String managerName;
}
