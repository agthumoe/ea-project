package edu.miu.cs544.moe.emr.domain.person;

import edu.miu.cs544.moe.emr.domain.shared.model.MutableModel;
import jakarta.persistence.*;

@MappedSuperclass
public class Person extends MutableModel {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
