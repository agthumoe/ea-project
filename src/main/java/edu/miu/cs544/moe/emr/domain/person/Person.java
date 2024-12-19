package edu.miu.cs544.moe.emr.domain.person;

import edu.miu.cs544.moe.emr.domain.shared.model.MutableModel;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@MappedSuperclass
public class Person extends MutableModel {
    private String name;
}
