package edu.miu.cs544.moe.emr.domain.investigation.quantitative.dto;

import edu.miu.cs544.moe.emr.domain.investigation.dto.InvestigationWithVisitResponse;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QuantitativeInvestigationWithVisitResponse extends InvestigationWithVisitResponse {
    private float result;
    private String unit;
    private String method;
}
