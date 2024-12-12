package edu.miu.cs544.moe.emr.domain.user.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateUser(@NotBlank String name, @NotBlank String username, @NotBlank String password) {
}
