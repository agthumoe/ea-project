package edu.miu.cs544.moe.emr.domain.investigation.dto;

import edu.miu.cs544.moe.emr.shared.dto.Dto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class InvestigationResponse extends Dto {
    private String uuid;
    private LocalDateTime investigationDate;
    private String investigationName;
    private String opinion;
}
