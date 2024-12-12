package edu.miu.cs544.moe.emr.domain.user;

import edu.miu.cs544.moe.emr.domain.user.dto.UserRequest;
import edu.miu.cs544.moe.emr.domain.user.dto.UserResponse;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserResponse> getAllUsers();
    Optional<UserResponse> getOneUser(Long id);
    UserResponse create(UserRequest user);
    UserResponse update(Long id, UserRequest user);
    void deleteById(Long id);
}
