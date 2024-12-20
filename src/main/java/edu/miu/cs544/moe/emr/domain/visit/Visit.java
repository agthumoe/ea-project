package edu.miu.cs544.moe.emr.domain.visit;

import edu.miu.cs544.moe.emr.domain.doctor.Doctor;
import edu.miu.cs544.moe.emr.domain.investigation.Investigation;
import edu.miu.cs544.moe.emr.domain.note.Note;
import edu.miu.cs544.moe.emr.domain.patient.Patient;
import edu.miu.cs544.moe.emr.domain.vital.Vital;
import edu.miu.cs544.moe.emr.shared.model.MutableModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "visits")
@Setter
@Getter
@NoArgsConstructor
public class Visit extends MutableModel {
    @Column(nullable = false, unique = true)
    private String uuid;
    @Column(nullable = false)
    private String complaint;
    @Column(name = "visit_date", nullable = false)
    private LocalDateTime visitDate;
    @ManyToOne
    private Doctor doctor;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Patient patient;
    private String diagnosis;
    @OneToMany(mappedBy = "visit", cascade = CascadeType.ALL)
    private List<Vital> vitals = new ArrayList<>();
    @OneToMany(mappedBy = "visit", cascade = CascadeType.ALL)
    private List<Note> notes = new ArrayList<>();
    @OneToMany(mappedBy = "visit", cascade = CascadeType.ALL)
    private List<Investigation> investigations = new ArrayList<>();
}
