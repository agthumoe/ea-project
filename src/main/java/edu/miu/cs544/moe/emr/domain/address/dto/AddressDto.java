package edu.miu.cs544.moe.emr.domain.address.dto;

import lombok.Data;

@Data
public class AddressDto {
    private String street;
    private String city;
    private String state;
    private String zipCode;
}
