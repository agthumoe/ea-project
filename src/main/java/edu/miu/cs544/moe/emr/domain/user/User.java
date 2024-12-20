package edu.miu.cs544.moe.emr.domain.user;

import edu.miu.cs544.moe.emr.domain.person.Person;
import edu.miu.cs544.moe.emr.shared.model.MutableModel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Setter
@Getter
@NoArgsConstructor
public class User extends MutableModel {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
    @Version
    private int version;
    public User(String name, String username, String password, Role role) {
        this.setName(name);
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
