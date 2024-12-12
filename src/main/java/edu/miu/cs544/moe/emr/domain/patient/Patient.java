package edu.miu.cs544.moe.emr.domain.patient;

import edu.miu.cs544.moe.emr.domain.address.Address;
import edu.miu.cs544.moe.emr.domain.shared.enums.BloodGroup;
import edu.miu.cs544.moe.emr.domain.shared.enums.Gender;
import edu.miu.cs544.moe.emr.domain.person.Person;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("patient")
@Table(name = "patients")
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public BloodGroup getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(BloodGroup bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
