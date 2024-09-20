package com.hr.management.model;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsersFull {
    private Long userId;
    private String userName;
    private String password;
    private Long roleId;
    private String roleName;


}