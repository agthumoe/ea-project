package edu.miu.cs544.moe.emr.domain.patient;

import edu.miu.cs544.moe.emr.domain.address.Address;
import edu.miu.cs544.moe.emr.domain.person.Person;
import edu.miu.cs544.moe.emr.domain.visit.Visit;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "patients")
@Setter
@Getter
@NoArgsConstructor
public class Patient extends Person {
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String phone;
    @Column(name = "date_of_birth", nullable = false)
    private LocalDate dateOfBirth;
    @Enumerated(EnumType.STRING)
    @Column(name = "blood_group", nullable = false)
    private BloodGroup bloodGroup;
    @Embedded
    private Address address;
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    private List<Visit> visits = new ArrayList<>();
    @Version
    private int version;

    public Patient(String name, Gender gender, String phone, LocalDate dateOfBirth, BloodGroup bloodGroup, Address address) {
        this.setName(name);
        this.gender = gender;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.bloodGroup = bloodGroup;
        this.address = address;
    }

    @Transient
    public String getAge() {
        LocalDate now = LocalDate.now();
        Period period = Period.between(dateOfBirth, now);
        int years = period.getYears();
        int months = period.getMonths();
        int days = period.getDays();

        List<String> parts = new ArrayList<>();
        if (years > 0) {
            parts.add(years + " year" + (years > 1 ? "s" : ""));
        }
        if (months > 0) {
            parts.add(months + " month" + (months > 1 ? "s" : ""));
        }
        if (days > 0) {
            parts.add(days + " day" + (days > 1 ? "s" : ""));
        }
        return String.join(", ", parts);
    }

    @Transient
    public String getYears() {
        LocalDate now = LocalDate.now();
        Period period = Period.between(dateOfBirth, now);
        return String.valueOf(period.getYears());
    }
}
