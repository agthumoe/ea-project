package edu.miu.cs544.moe.emr.domain.user;

import edu.miu.cs544.moe.emr.application.Mapper;
import edu.miu.cs544.moe.emr.domain.user.dto.CreateUser;
import edu.miu.cs544.moe.emr.domain.user.dto.UpdateUser;
import edu.miu.cs544.moe.emr.domain.user.dto.UserResponse;
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
    private Mapper mapper;

    @Override
    public List<UserResponse> getAllUsers() {
        return this.mapper.map(this.repository.findAll(), UserResponse.class);
    }

    @Override
    public Optional<UserResponse> getOneUser(Long id) {
        return this.repository.findById(id).map(user -> this.mapper.map(user, UserResponse.class));
    }

    @Override
    public UserResponse create(CreateUser user) {
        return this.mapper.map(this.repository.save(this.mapper.map(user, User.class)), UserResponse.class);
    }

    @Override
    @Transactional
    public UserResponse update(Long id, UpdateUser dto) {
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
