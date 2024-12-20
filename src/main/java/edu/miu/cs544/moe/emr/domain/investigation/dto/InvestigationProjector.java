package edu.miu.cs544.moe.emr.domain.investigation.dto;

import java.time.LocalDateTime;

public interface InvestigationProjector {
    Long getId();
    LocalDateTime getCreatedDate();
    String getCreatedBy();
    LocalDateTime getLastModifiedDate();
    String getLastModifiedBy();
    String getUuid();
    Class<?> getDiscriminator();
    LocalDateTime getInvestigationDate();
    String getInvestigationName();
    String getOpinion();
}
