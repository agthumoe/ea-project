package edu.miu.cs544.moe.emr.domain.vital.dto;

import edu.miu.cs544.moe.emr.domain.visit.dto.VisitResponse;
import edu.miu.cs544.moe.emr.shared.dto.Dto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VitalResponse extends Dto {
    private int pulseRate;
    private int respiratoryRate;
    private int systolicBloodPressure;
    private int diastolicBloodPressure;
    private float temperature;
    private float spo2;
    private float randomBloodSugar;
    private float fastingBloodSugar;
    private VisitResponse visit;
}
