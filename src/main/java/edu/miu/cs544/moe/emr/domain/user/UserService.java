package edu.miu.cs544.moe.emr.domain.user;

import edu.miu.cs544.moe.emr.domain.user.dto.CreateUser;
import edu.miu.cs544.moe.emr.domain.user.dto.UpdateUser;
import edu.miu.cs544.moe.emr.domain.user.dto.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> getAll();
    UserResponse getOne(Long id);
    UserResponse create(CreateUser user);
    UserResponse update(Long id, UpdateUser user);
    UserResponse updatePassword(Long id, String oldPassword, String newPassword);
    void delete(Long id);
}
