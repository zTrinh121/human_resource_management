package com.hr.management.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hr.management.model.Roles;
import com.hr.management.request.RolesRequest;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RolesResponse {

    @JsonProperty("role_id")
    private Long roleId;

    @JsonProperty("role_name")
    private String roleName;

    public static RolesResponse fromRoles(Roles roles){
        return RolesResponse
                .builder()
                .roleId(roles.getRoleId())
                .roleName(roles.getRoleName())
                .build();
    }

    public static RolesResponse fromRoleRequest(RolesRequest rolesRequest){
        return RolesResponse
                .builder()
                .roleName(rolesRequest.getRoleName())
                .build();
    }
}
