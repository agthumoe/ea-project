package edu.miu.cs544.moe.emr.domain.investigation.descriptive.dto;

import edu.miu.cs544.moe.emr.domain.investigation.dto.InvestigationWithVisitResponse;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DescriptiveInvestigationWithVisitResponse extends InvestigationWithVisitResponse {
    private String result;
    private String details;
}
