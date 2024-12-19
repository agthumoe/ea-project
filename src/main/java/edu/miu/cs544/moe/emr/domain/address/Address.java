package edu.miu.cs544.moe.emr.domain.address;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Embeddable
public class Address {
    private String street;
    private String city;
    private String state;
    @Column(name = "zip_code")
    private String zipCode;
}
