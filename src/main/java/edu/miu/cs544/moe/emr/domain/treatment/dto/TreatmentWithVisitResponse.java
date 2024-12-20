package edu.miu.cs544.moe.emr.domain.treatment.dto;

import edu.miu.cs544.moe.emr.domain.visit.dto.VisitResponse;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TreatmentWithVisitResponse extends TreatmentResponse{
    private VisitResponse visit;
}
