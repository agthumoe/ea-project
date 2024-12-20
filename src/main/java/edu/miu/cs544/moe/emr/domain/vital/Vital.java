package edu.miu.cs544.moe.emr.domain.vital;

import edu.miu.cs544.moe.emr.domain.visit.Visit;
import edu.miu.cs544.moe.emr.shared.model.MutableModel;
import jakarta.persistence.*;
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
    private Integer pulseRate;
    @Column(name = "respiratory_rate")
    private Integer respiratoryRate;
    @Column(name = "systolic_blood_pressure")
    private Integer systolicBloodPressure;
    @Column(name = "diastolic_blood_pressure")
    private Integer diastolicBloodPressure;
    private Float temperature;
    private Float spo2;
    @Column(name = "random_blood_sugar")
    private Float randomBloodSugar;
    @Column(name = "fasting_blood_sugar")
    private Float fastingBloodSugar;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false, updatable = false, unique = true)
    private Visit visit;
    @Version
    private int version;

    public Vital(Float spo2, Integer pulseRate, Integer respiratoryRate, Integer systolicBloodPressure, Integer diastolicBloodPressure, Float temperature, Float randomBloodSugar, Float fastingBloodSugar, Visit visit) {
        this.spo2 = spo2;
        this.pulseRate = pulseRate;
        this.respiratoryRate = respiratoryRate;
        this.systolicBloodPressure = systolicBloodPressure;
        this.diastolicBloodPressure = diastolicBloodPressure;
        this.temperature = temperature;
        this.randomBloodSugar = randomBloodSugar;
        this.fastingBloodSugar = fastingBloodSugar;
        this.visit = visit;
    }
}
