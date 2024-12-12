package edu.miu.cs544.moe.emr.domain.user.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateUser(@NotBlank String name) {
}
