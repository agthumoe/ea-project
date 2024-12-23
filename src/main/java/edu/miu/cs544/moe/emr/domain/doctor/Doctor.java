package edu.miu.cs544.moe.emr.domain.doctor;

import edu.miu.cs544.moe.emr.domain.person.Person;
import edu.miu.cs544.moe.emr.domain.visit.Visit;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "doctors")
@Setter
@Getter
@NoArgsConstructor
public class Doctor extends Person {
    private String speciality;
    @Column(name = "license_number", nullable = false, unique = true)
    private String licenseNumber;
    private String phone;
    @Version
    private int version;
    @OneToMany(mappedBy = "doctor")
    private List<Visit> visits = new ArrayList<>();
}
