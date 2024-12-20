package edu.miu.cs544.moe.emr.domain.investigation.descriptive.dto;

import edu.miu.cs544.moe.emr.domain.investigation.dto.InvestigationResponse;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DescriptiveInvestigationResponse extends InvestigationResponse {
    private String result;
    private String details;
}
