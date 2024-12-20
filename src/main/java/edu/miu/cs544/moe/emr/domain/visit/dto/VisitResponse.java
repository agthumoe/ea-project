package edu.miu.cs544.moe.emr.domain.visit.dto;

import edu.miu.cs544.moe.emr.shared.dto.Dto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class VisitResponse extends Dto {
    private String uuid;
    private String complaint;
    private LocalDateTime visitDate;
    private String notes;
    private String provisionalDiagnosis;
    private String finalDiagnosis;

}
