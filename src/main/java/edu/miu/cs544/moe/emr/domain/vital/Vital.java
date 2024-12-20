package edu.miu.cs544.moe.emr.domain.vital;

import edu.miu.cs544.moe.emr.domain.visit.Visit;
import edu.miu.cs544.moe.emr.shared.model.MutableModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "vitals")
@Setter
@Getter
@NoArgsConstructor
public class Vital extends MutableModel {
    @Column(name = "pulse_rate")
    private int pulseRate;
    @Column(name = "respiratory_rate")
    private int respiratoryRate;
    @Column(name = "systolic_blood_pressure")
    private int systolicBloodPressure;
    @Column(name = "diastolic_blood_pressure")
    private int diastolicBloodPressure;
    private float temperature;
    private float weight;
    private float spo2;
    @Column(name = "random_blood_sugar")
    private float randomBloodSugar;
    @Column(name = "fasting_blood_sugar")
    private float fastingBloodSugar;
    @ManyToOne
    private Visit visit;

    public Vital(int pulseRate, int respiratoryRate, int systolicBloodPressure, int diastolicBloodPressure, float temperature, float weight, float spo2, float randomBloodSugar, float fastingBloodSugar) {
        this.pulseRate = pulseRate;
        this.respiratoryRate = respiratoryRate;
        this.systolicBloodPressure = systolicBloodPressure;
        this.diastolicBloodPressure = diastolicBloodPressure;
        this.temperature = temperature;
        this.weight = weight;
        this.spo2 = spo2;
        this.randomBloodSugar = randomBloodSugar;
        this.fastingBloodSugar = fastingBloodSugar;
    }
}
