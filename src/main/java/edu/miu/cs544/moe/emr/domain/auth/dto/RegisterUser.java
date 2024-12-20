package edu.miu.cs544.moe.emr.domain.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterUser {
    @NotBlank(message = "{user.validations.name.required}")
    private String name;
    @NotBlank(message = "{user.validations.username.required}")
    private String username;
    @NotBlank(message = "{user.validations.password.required}")
    private String password;
}
