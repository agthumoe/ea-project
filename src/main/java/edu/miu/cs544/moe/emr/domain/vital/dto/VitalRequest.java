package edu.miu.cs544.moe.emr.domain.vital.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VitalRequest {
    @Positive(message = "{vital.validations.positive}")
    private Integer pulseRate;
    @Positive(message = "{vital.validations.positive}")
    private Integer respiratoryRate;
    @Positive(message = "{vital.validations.positive}")
    private Integer systolicBloodPressure;
    @Positive(message = "{vital.validations.positive}")
    private Integer diastolicBloodPressure;
    @Positive(message = "{vital.validations.positive}")
    private Float temperature;
    @DecimalMax(value = "100", message = "{vital.validations.percentage}")
    @DecimalMin(value = "0", message = "{vital.validations.percentage}")
    private Float spo2;
    @Positive(message = "{vital.validations.positive}")
    private Float randomBloodSugar;
    @Positive(message = "{vital.validations.positive}")
    private Float fastingBloodSugar;
}
