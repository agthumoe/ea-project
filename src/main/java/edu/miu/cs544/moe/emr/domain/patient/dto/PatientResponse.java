package edu.miu.cs544.moe.emr.domain.patient.dto;

import edu.miu.cs544.moe.emr.domain.patient.BloodGroup;
import edu.miu.cs544.moe.emr.domain.patient.Gender;
import edu.miu.cs544.moe.emr.shared.dto.Dto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Setter
@Getter
@ToString
public class PatientResponse extends Dto {
    private String uuid;
    private String name;
    private Gender gender;
    private String phone;
    private LocalDate dateOfBirth;
    private BloodGroup bloodGroup;
    private String age;
    private AddressDto address;
}
