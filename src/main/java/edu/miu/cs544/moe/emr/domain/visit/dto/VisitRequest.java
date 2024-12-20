package edu.miu.cs544.moe.emr.domain.visit.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class VisitRequest {
    @NotBlank
    private String complaint;
    @NotNull
    @FutureOrPresent
    private LocalDateTime visitDate;
    private String notes;
    private String provisionalDiagnosis;
    private String finalDiagnosis;
    @NotNull
    private Long doctorId;
    @NotNull
    private Long patientId;
}
