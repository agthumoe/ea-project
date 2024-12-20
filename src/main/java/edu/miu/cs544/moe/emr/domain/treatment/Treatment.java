package edu.miu.cs544.moe.emr.domain.treatment;

import edu.miu.cs544.moe.emr.domain.visit.Visit;
import edu.miu.cs544.moe.emr.shared.model.MutableModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "treatments")
@Setter
@Getter
@NoArgsConstructor
public class Treatment extends MutableModel {
    @Column(nullable = false, unique = true, updatable = false)
    private String uuid;
    @Column(name = "treatment_date", nullable = false)
    private LocalDateTime treatmentDate;
    @Column(nullable = false)
    private String name;
    @Column(columnDefinition = "TEXT")
    private String details;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Visit visit;
}
