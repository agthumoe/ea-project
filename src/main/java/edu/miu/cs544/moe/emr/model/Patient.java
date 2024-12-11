package edu.miu.cs544.moe.emr.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("patient")
@Table(name = "patients")
public class Patient extends Individual {
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
}
