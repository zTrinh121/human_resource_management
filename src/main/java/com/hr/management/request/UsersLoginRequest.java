package com.hr.management.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsersLoginRequest {

    @NotEmpty(message = "User name is required")
    @JsonProperty("user_name")
    private String userName;

    @NotEmpty(message = "Password is required")
    @JsonProperty("password")
    private String password;
}
