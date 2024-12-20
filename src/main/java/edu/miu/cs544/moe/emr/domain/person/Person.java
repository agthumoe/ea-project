package edu.miu.cs544.moe.emr.domain.person;

import edu.miu.cs544.moe.emr.shared.model.MutableModel;
import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@MappedSuperclass
public class Person extends MutableModel {
    @Column(nullable = false, unique = true, updatable = false)
    private String uuid;
    @Column(nullable = false)
    private String name;
}
