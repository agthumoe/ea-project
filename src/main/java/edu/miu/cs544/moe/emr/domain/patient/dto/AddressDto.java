package edu.miu.cs544.moe.emr.domain.patient.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddressDto {
    private String street;
    private String city;
    private String state;
    private String zipCode;
}
