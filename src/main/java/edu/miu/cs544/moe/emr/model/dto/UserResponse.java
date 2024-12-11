package edu.miu.cs544.moe.emr.model.dto;

import java.time.LocalDateTime;

public record UserResponse(Long id, LocalDateTime createdDate, String createdBy, LocalDateTime lastModifiedDate, String lastModifiedBy, String name, String username) {
}
