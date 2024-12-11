package edu.miu.cs544.moe.emr.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@DiscriminatorValue("user")
@Table(name = "users")
public class User extends Individual {
    private String username;
    private String password;
}
