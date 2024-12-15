package edu.miu.cs544.moe.emr.domain.user;

import edu.miu.cs544.moe.emr.domain.person.Person;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@DiscriminatorValue("user")
@Table(name = "users")
public class User extends Person {
    private String username;
    private String password;
}
