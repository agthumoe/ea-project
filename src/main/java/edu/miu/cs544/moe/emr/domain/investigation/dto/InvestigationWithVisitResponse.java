package edu.miu.cs544.moe.emr.domain.investigation.dto;

import edu.miu.cs544.moe.emr.domain.visit.dto.VisitResponse;
import edu.miu.cs544.moe.emr.shared.dto.Dto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class InvestigationWithVisitResponse extends Dto {
    private String uuid;
    private LocalDateTime investigationDate;
    private String investigationName;
    private String opinion;
    private VisitResponse visit;
}
