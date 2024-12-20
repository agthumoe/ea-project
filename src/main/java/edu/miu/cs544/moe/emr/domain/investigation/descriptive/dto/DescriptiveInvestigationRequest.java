package edu.miu.cs544.moe.emr.domain.investigation.descriptive.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class DescriptiveInvestigationRequest {
    @NotNull(message = "{investigation.validations.investigationDate.required}")
    private LocalDateTime investigationDate;
    @NotBlank(message = "{investigation.validations.investigationName.required}")
    private String investigationName;
    private String opinion;
    private String result;
    private String details;
}
