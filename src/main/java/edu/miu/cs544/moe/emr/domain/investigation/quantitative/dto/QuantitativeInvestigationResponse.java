package edu.miu.cs544.moe.emr.domain.investigation.quantitative.dto;

import edu.miu.cs544.moe.emr.domain.investigation.dto.InvestigationResponse;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QuantitativeInvestigationResponse extends InvestigationResponse {
    private float result;
    private String unit;
    private String method;
}
