package edu.miu.cs544.moe.emr.domain.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdatePassword(
        @NotBlank(message = "{user.validations.oldPassword.required}") String oldPassword,
        @NotBlank(message = "{user.validations.password.required}") String newPassword) {
}
