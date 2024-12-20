package edu.miu.cs544.moe.emr.domain.user.dto;

import edu.miu.cs544.moe.emr.domain.user.Role;
import edu.miu.cs544.moe.emr.shared.dto.Dto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class UserResponse extends Dto {
    private String name;
    private String username;
    private Role role;
}
