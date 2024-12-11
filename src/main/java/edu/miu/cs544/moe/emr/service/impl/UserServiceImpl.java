package edu.miu.cs544.moe.emr.service.impl;

import edu.miu.cs544.moe.emr.common.CustomMapper;
import edu.miu.cs544.moe.emr.model.User;
import edu.miu.cs544.moe.emr.model.dto.UserRequest;
import edu.miu.cs544.moe.emr.model.dto.UserResponse;
import edu.miu.cs544.moe.emr.repository.UserRepository;
import edu.miu.cs544.moe.emr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private CustomMapper mapper;

    @Override
    public List<UserResponse> getAllUsers() {
        return this.mapper.map(this.repository.findAll(), UserResponse.class);
    }

    @Override
    public Optional<UserResponse> getOneUser(Long id) {
        return this.repository.findById(id).map(user -> this.mapper.map(user, UserResponse.class));
    }

    @Override
    public UserResponse create(UserRequest user) {
        return this.mapper.map(this.repository.save(this.mapper.map(user, User.class)), UserResponse.class);
    }

    @Override
    @Transactional
    public UserResponse update(Long id, UserRequest dto) {
        User user = this.repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
        this.mapper.map(dto, user);
        this.repository.save(user);
        return this.mapper.map(user, UserResponse.class);
    }

    @Override
    public void deleteById(Long id) {
        this.repository.deleteById(id);
    }
}
