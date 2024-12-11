package edu.miu.cs544.moe.emr.model;

import edu.miu.cs544.moe.emr.common.MutableModel;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type")
@Table(name = "persons")
public class Person extends MutableModel {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
