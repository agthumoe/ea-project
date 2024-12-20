package edu.miu.cs544.moe.emr.domain.investigation.descriptive;

import edu.miu.cs544.moe.emr.domain.investigation.Investigation;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "descriptive_investigations")
@Setter
@Getter
@NoArgsConstructor
@DiscriminatorValue("DESCRIPTIVE")
public class DescriptiveInvestigation extends Investigation {
    private String result;
    private String details;
}
