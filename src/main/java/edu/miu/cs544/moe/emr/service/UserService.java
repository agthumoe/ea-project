package edu.miu.cs544.moe.emr.service;

import edu.miu.cs544.moe.emr.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    Optional<User> getOneUser(Long id);
    User create(User user);
    User update(Long id, User user);
    void deleteById(Long id);
    void delete(User user);
}
