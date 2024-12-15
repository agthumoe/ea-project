package edu.miu.cs544.moe.emr.domain.category;

import edu.miu.cs544.moe.emr.domain.patient.Patient;
import edu.miu.cs544.moe.emr.domain.shared.model.MutableModel;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class Category extends MutableModel {
    private String name;
    private String description;

    @EqualsAndHashCode.Exclude
    @OneToMany
    private List<Patient> patients = new ArrayList<>();
}
