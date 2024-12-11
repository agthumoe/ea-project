package edu.miu.cs544.moe.emr.service.impl;

import edu.miu.cs544.moe.emr.model.User;
import edu.miu.cs544.moe.emr.repository.UserRepository;
import edu.miu.cs544.moe.emr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;

    @Override
    public List<User> getAllUsers() {
        return this.repository.findAll();
    }

    @Override
    public Optional<User> getOneUser(Long id) {
        return this.repository.findById(id);
    }

    @Override
    public User create(User user) {
        return this.repository.save(user);
    }

    @Override
    public User update(Long id, User user) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        this.repository.deleteById(id);
    }

    @Override
    public void delete(User user) {
        this.repository.delete(user);
    }
}
