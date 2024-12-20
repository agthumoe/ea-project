package edu.miu.cs544.moe.emr.domain.treatment.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class TreatmentRequest {
    @NotNull(message = "{treatment.validations.treatmentDate.required}")
    private LocalDateTime treatmentDate;
    @NotNull(message = "{treatment.validations.name.required}")
    private String name;
    private String details;
}
