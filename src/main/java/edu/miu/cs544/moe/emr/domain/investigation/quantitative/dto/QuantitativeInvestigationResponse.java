package edu.miu.cs544.moe.emr.domain.investigation.quantitative.dto;

import edu.miu.cs544.moe.emr.domain.visit.dto.VisitResponse;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class QuantitativeInvestigationResponse {
    private String uuid;
    private LocalDateTime investigationDate;
    private String investigationName;
    private String opinion;
    private float result;
    private String unit;
    private String method;
    private VisitResponse visit;
}
