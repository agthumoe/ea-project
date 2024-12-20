package edu.miu.cs544.moe.emr.domain.visit.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class VisitRequest {
    @NotBlank(message = "{visit.validations.complaint.required}")
    private String complaint;
    @NotNull(message = "{visit.validations.visitDate.required}")
    @FutureOrPresent(message = "{visit.validations.visitDate.futureOrPresent}")
    private LocalDateTime visitDate;
    private String notes;
    private String provisionalDiagnosis;
    private String finalDiagnosis;
    @NotNull(message = "{visit.validations.doctor.required}")
    private Long doctorId;
    @NotNull(message = "{visit.validations.patient.required}")
    private Long patientId;
}
