package edu.miu.cs544.moe.emr.domain.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class LoginRequest {
    @NotBlank(message = "{user.validations.username.required}")
    private String username;
    @NotBlank(message = "{user.validations.password.required}")
    private String password;
}
