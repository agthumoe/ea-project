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
    @ManyToOne
    private Visit visit;

    public Vital(Integer pulseRate, Integer respiratoryRate, Integer systolicBloodPressure, Integer diastolicBloodPressure, Float temperature, Float spo2, Float randomBloodSugar, Float fastingBloodSugar) {
        this.pulseRate = pulseRate;
        this.respiratoryRate = respiratoryRate;
        this.systolicBloodPressure = systolicBloodPressure;
        this.diastolicBloodPressure = diastolicBloodPressure;
        this.temperature = temperature;
        this.spo2 = spo2;
        this.randomBloodSugar = randomBloodSugar;
        this.fastingBloodSugar = fastingBloodSugar;
    }
}
