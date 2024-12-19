package edu.miu.cs544.moe.emr.domain.patient;

import edu.miu.cs544.moe.emr.domain.address.Address;
import edu.miu.cs544.moe.emr.domain.shared.enums.BloodGroup;
import edu.miu.cs544.moe.emr.domain.shared.enums.Gender;
import edu.miu.cs544.moe.emr.domain.person.Person;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "patients")
@Setter
@Getter
@NoArgsConstructor
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

    public Patient(String name, Gender gender, String phone, LocalDate dateOfBirth, BloodGroup bloodGroup, Address address) {
        this.setName(name);
        this.gender = gender;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.bloodGroup = bloodGroup;
        this.address = address;
    }
}
