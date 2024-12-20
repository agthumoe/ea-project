package edu.miu.cs544.moe.emr.domain.patient.dto;

import edu.miu.cs544.moe.emr.domain.patient.BloodGroup;
import edu.miu.cs544.moe.emr.domain.patient.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public class PatientRequest {
    @NotBlank(message = "{patient.validations.name.required}")
    private String name;
    @NotNull(message = "{patient.validations.gender.required}")
    private Gender gender;
    private String phone;
    @NotNull(message = "{patient.validations.dateOfBirth.required}")
    @Past(message = "{patient.validations.dateOfBirth.past}")
    private LocalDate dateOfBirth;
    @NotNull(message = "{patient.validations.bloodGroup.required}")
    private BloodGroup bloodGroup;
    private AddressDto address;
}
