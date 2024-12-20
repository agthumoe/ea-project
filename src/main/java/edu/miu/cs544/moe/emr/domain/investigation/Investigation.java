package edu.miu.cs544.moe.emr.domain.investigation;

import edu.miu.cs544.moe.emr.domain.visit.Visit;
import edu.miu.cs544.moe.emr.shared.model.MutableModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "investigations")
@Setter
@Getter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "investigation_type")
public abstract class Investigation extends MutableModel {
    @Column(nullable = false, unique = true)
    private String uuid;
    @Column(name = "investigation_date", nullable = false)
    private LocalDateTime investigationDate;
    @Column(name = "investigation_name", nullable = false)
    private String investigationName;
    private String opinion;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Visit visit;
}
