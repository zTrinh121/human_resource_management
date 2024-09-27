package com.hr.management.model;

import com.hr.management.request.RolesRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Roles {
    private Long roleId;
    private String roleName;
    public Long getRoleId() {
        return roleId;
    }
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
    public String getRoleName() {
        return roleName;
    }
    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public static Roles fromRoleRequest(RolesRequest rolesRequest){
        return Roles
                .builder()
                .roleName(rolesRequest.getRoleName())
                .build();
    }

    public static String ADMIN = "ADMIN";
    public static String USER = "USER";
}