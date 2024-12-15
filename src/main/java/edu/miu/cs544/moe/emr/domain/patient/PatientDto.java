package edu.miu.cs544.moe.emr.domain.patient;

import edu.miu.cs544.moe.emr.domain.address.dto.AddressDto;
import edu.miu.cs544.moe.emr.domain.shared.enums.BloodGroup;
import edu.miu.cs544.moe.emr.domain.shared.enums.Gender;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PatientDto {
    private Long id;
    private LocalDate createdDate;
    private String name;
    private Gender gender;
    private String phone;
    private LocalDate dateOfBirth;
    private BloodGroup bloodGroup;
    private AddressDto address;
}
