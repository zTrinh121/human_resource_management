package com.hr.management.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hr.management.model.Roles;
import com.hr.management.model.Users;
import com.hr.management.model.UsersFull;
import com.hr.management.request.RolesRequest;
import com.hr.management.request.UsersRequest;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsersResponse {

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("user_name")
    private String userName;

    @JsonProperty("password")
    private String password;

    @JsonProperty("role_id")
    private Long roleId;
    
    @JsonProperty("role_name")
    private String roleName;

    public static UsersResponse fromUsers(Users users){
        return UsersResponse
                .builder()
                .userId(users.getUserId())
                .userName(users.getUserName())
                .password(users.getPassword())
                .roleId(users.getRoleId())
                .build();
    }

    public static UsersResponse fromUserRequest(UsersRequest usersRequest){
        return UsersResponse
                .builder()
                .userName(usersRequest.getUserName())
                .password(usersRequest.getPassword())
                .roleId(usersRequest.getRoleId())
                .build();
    }

    public static UsersResponse fromUserFull(UsersFull usersFull){
        return UsersResponse
                .builder()
                .userId(usersFull.getUserId())
                .userName(usersFull.getUserName())
                .password(usersFull.getPassword())
                .roleId(usersFull.getRoleId())
                .roleName(usersFull.getRoleName())
                .build();
    }
}
