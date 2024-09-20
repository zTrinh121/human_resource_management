package com.hr.management.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RolesRequest {

    @NotEmpty(message = "Role name is required")
    @JsonProperty("role_name")
    private String roleName;
}
