package com.hr.management.model;

import com.hr.management.request.UsersRequest;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Users {
    private Long userId;
    private String userName;
    private String password;
    private Long roleId;
    private String imgUrl;
    private String publicId;

    public static Users fromUserRequest(UsersRequest usersRequest){
        return Users.builder()
                .userName(usersRequest.getUserName())
                .password(usersRequest.getPassword())
                .roleId(usersRequest.getRoleId())
                .build();
    }



}