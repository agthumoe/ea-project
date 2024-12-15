package edu.miu.cs544.moe.emr.domain.category;

import edu.miu.cs544.moe.emr.domain.patient.Patient;
import edu.miu.cs544.moe.emr.domain.shared.model.MutableModel;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Category extends MutableModel {
    private String name;
    private String description;

    @EqualsAndHashCode.Exclude
    @OneToMany
    private List<Patient> patients = new ArrayList<>();

    public Category() {
    }

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }
}
