package edu.miu.cs544.moe.emr.domain.user.dto;

import edu.miu.cs544.moe.emr.domain.user.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateUserRequest {
    @NotBlank(message = "{user.validations.name.required}")
    private String name;
    @NotBlank(message = "{user.validations.username.required}")
    private String username;
    @NotBlank(message = "{user.validations.password.required}")
    private String password;
    @NotNull(message = "{user.validations.role.required}")
    private Role role;
}
