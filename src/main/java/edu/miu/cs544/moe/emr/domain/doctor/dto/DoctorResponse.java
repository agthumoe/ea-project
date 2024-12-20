package edu.miu.cs544.moe.emr.domain.doctor.dto;

import edu.miu.cs544.moe.emr.shared.dto.Dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoctorResponse extends Dto {
    private String uuid;
    private String name;
    private String speciality;
    private String licenseNumber;
}
