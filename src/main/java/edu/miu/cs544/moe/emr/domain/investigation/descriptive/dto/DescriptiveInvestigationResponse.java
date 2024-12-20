package edu.miu.cs544.moe.emr.domain.investigation.descriptive.dto;

import edu.miu.cs544.moe.emr.domain.visit.dto.VisitResponse;
import edu.miu.cs544.moe.emr.shared.dto.Dto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class DescriptiveInvestigationResponse extends Dto {
    private String uuid;
    private LocalDateTime investigationDate;
    private String investigationName;
    private String opinion;
    private String result;
    private String details;
    private VisitResponse visit;
}
