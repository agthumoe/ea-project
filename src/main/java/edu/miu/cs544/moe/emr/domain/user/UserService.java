package edu.miu.cs544.moe.emr.domain.user;

import edu.miu.cs544.moe.emr.domain.user.dto.CreateUser;
import edu.miu.cs544.moe.emr.domain.user.dto.UpdateUser;
import edu.miu.cs544.moe.emr.domain.user.dto.UserResponse;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserResponse> getAllUsers();
    Optional<UserResponse> getOneUser(Long id);
    UserResponse create(CreateUser user);
    UserResponse update(Long id, UpdateUser user);
    void deleteById(Long id);
}
