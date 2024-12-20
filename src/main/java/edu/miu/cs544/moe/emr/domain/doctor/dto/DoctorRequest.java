package edu.miu.cs544.moe.emr.domain.doctor.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoctorRequest {
    @NotBlank(message = "{doctor.validations.name.required}")
    private String name;
    private String speciality;
    @NotBlank(message = "{doctor.validations.licenseNumber.required}")
    private String licenseNumber;
}
