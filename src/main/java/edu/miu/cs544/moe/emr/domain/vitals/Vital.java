package edu.miu.cs544.moe.emr.domain.vitals;

import edu.miu.cs544.moe.emr.domain.patient.Patient;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Vital {
    @Id
    private Long id;
    @OneToOne
    @MapsId
    private Patient patient;

    private double temperature;
    private double bloodPressure;
    private double pulse;
    private double weight;
    private double height;
    private double bmi;
    private double oxygenLevel;
    private double respiratoryRate;
    private double glucoseLevel;
}
