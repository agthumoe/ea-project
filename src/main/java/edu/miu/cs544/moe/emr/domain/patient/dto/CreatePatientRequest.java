package edu.miu.cs544.moe.emr.domain.patient.dto;

import edu.miu.cs544.moe.emr.domain.shared.enums.BloodGroup;
import edu.miu.cs544.moe.emr.domain.shared.enums.Gender;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public class CreatePatientRequest {
    @NotBlank
    private String name;
    private Gender gender;
    private String phone;
    private LocalDate dateOfBirth;
//    @NotBlank(message = "{patient.validations.bloodGroup.required}")
    private BloodGroup bloodGroup;
    private AddressDto address;
}
