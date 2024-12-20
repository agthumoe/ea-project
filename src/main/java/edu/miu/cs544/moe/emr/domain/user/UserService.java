package edu.miu.cs544.moe.emr.domain.user;

import edu.miu.cs544.moe.emr.domain.user.dto.CreateUserRequest;
import edu.miu.cs544.moe.emr.domain.user.dto.UpdateUserRequest;
import edu.miu.cs544.moe.emr.domain.user.dto.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    Page<UserResponse> getAll(Pageable pageable);
    UserResponse getOne(Long id);
    UserResponse create(CreateUserRequest user);
    UserResponse update(Long id, UpdateUserRequest user);
    UserResponse updatePassword(Long id, String oldPassword, String newPassword);
    void delete(Long id);
}
