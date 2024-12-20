package edu.miu.cs544.moe.emr.domain.user.dto;

import edu.miu.cs544.moe.emr.domain.user.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateUserRequest {
    @NotBlank(message = "{user.validations.name.required}")
    private String name;
    @NotNull(message = "{user.validations.role.required}")
    private Role role;
}
