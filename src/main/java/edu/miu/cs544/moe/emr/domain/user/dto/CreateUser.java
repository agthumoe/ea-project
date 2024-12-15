package edu.miu.cs544.moe.emr.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateUser {
    @NotBlank
    private String name;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
