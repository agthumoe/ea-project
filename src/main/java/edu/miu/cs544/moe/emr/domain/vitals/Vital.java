package edu.miu.cs544.moe.emr.domain.vitals;

import edu.miu.cs544.moe.emr.domain.patient.Patient;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
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

    public Vital() {
    }

    public Vital(Patient patient, double temperature, double bloodPressure, double pulse, double weight, double height, double bmi, double oxygenLevel, double respiratoryRate, double glucoseLevel) {
        this.patient = patient;
        this.temperature = temperature;
        this.bloodPressure = bloodPressure;
        this.pulse = pulse;
        this.weight = weight;
        this.height = height;
        this.bmi = bmi;
        this.oxygenLevel = oxygenLevel;
        this.respiratoryRate = respiratoryRate;
        this.glucoseLevel = glucoseLevel;
    }
}
