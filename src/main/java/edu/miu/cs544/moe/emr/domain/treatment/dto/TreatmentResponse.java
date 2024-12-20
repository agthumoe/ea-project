package edu.miu.cs544.moe.emr.domain.treatment.dto;

import edu.miu.cs544.moe.emr.shared.dto.Dto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class TreatmentResponse extends Dto {
    private String uuid;
    private LocalDateTime treatmentDate;
    private String name;
    private String details;
}
