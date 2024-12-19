package edu.miu.cs544.moe.emr.domain.auth.dto;

public record TokenResponse(String accessToken, String refreshToken) {
}
