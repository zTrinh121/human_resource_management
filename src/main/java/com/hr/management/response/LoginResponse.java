package com.hr.management.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponse {
    @JsonProperty("token")
    private String departmentId;

    @JsonProperty("role_name")
    private String roleName;

    @JsonProperty("user_name")
    private String userName;

}
