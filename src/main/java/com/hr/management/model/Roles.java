package com.hr.management.model;

import com.hr.management.request.RolesRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Roles {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ROLES.ROLE_ID
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    private Long roleId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ROLES.ROLE_NAME
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    private String roleName;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ROLES.ROLE_ID
     *
     * @return the value of ROLES.ROLE_ID
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ROLES.ROLE_ID
     *
     * @param roleId the value for ROLES.ROLE_ID
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ROLES.ROLE_NAME
     *
     * @return the value of ROLES.ROLE_NAME
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ROLES.ROLE_NAME
     *
     * @param roleName the value for ROLES.ROLE_NAME
     *
     * @mbg.generated Mon Sep 16 13:30:33 ICT 2024
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public static Roles fromRoleRequest(RolesRequest rolesRequest){
        return Roles
                .builder()
                .roleName(rolesRequest.getRoleName())
                .build();
    }
}