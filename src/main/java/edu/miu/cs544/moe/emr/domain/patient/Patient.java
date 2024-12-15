package edu.miu.cs544.moe.emr.domain.patient;

import edu.miu.cs544.moe.emr.domain.address.Address;
import edu.miu.cs544.moe.emr.domain.shared.enums.BloodGroup;
import edu.miu.cs544.moe.emr.domain.shared.enums.Gender;
import edu.miu.cs544.moe.emr.domain.person.Person;
import edu.miu.cs544.moe.emr.domain.vitals.Vital;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("patient")
@Table(name = "patients")
@Data
public class Patient extends Person {
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String phone;
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    @Enumerated(EnumType.STRING)
    @Column(name = "blood_group")
    private BloodGroup bloodGroup;
    @Embedded
    private Address address;

    @OneToOne(mappedBy = "patient", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Vital vital;
}
