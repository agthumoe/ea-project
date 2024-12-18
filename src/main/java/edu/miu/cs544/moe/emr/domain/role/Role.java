package edu.miu.cs544.moe.emr.domain.role;

import edu.miu.cs544.moe.emr.domain.shared.model.Model;
import jakarta.persistence.Entity;
import org.springframework.security.core.GrantedAuthority;

@Entity
public class Role extends Model implements GrantedAuthority {
    private String name;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return this.name;
    }
}
