package edu.miu.cs544.moe.emr.domain.investigation.quantitative;

import edu.miu.cs544.moe.emr.domain.investigation.Investigation;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "quantitative_investigations")
@Setter
@Getter
@NoArgsConstructor
@DiscriminatorValue("QUANTITATIVE")
public class QuantitativeInvestigation extends Investigation {
    private float result;
    private String unit;
    private String method;
}
