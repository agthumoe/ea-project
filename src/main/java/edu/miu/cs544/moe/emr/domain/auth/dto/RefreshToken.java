package edu.miu.cs544.moe.emr.domain.auth.dto;

import jakarta.validation.constraints.NotBlank;

public record RefreshToken(@NotBlank String token) {
}
