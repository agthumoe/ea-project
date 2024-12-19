package edu.miu.cs544.moe.emr.domain.user;

import edu.miu.cs544.moe.emr.exception.NotFoundException;
import edu.miu.cs544.moe.emr.exception.UnauthorizedException;
import edu.miu.cs544.moe.emr.helper.LocaleMessageProvider;
import edu.miu.cs544.moe.emr.helper.Mapper;
import edu.miu.cs544.moe.emr.domain.user.dto.CreateUser;
import edu.miu.cs544.moe.emr.domain.user.dto.UpdateUser;
import edu.miu.cs544.moe.emr.domain.user.dto.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    @Autowired
    private LocaleMessageProvider messageSource;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<UserResponse> getAll() {
        return this.mapper.map(this.repository.findAll(), UserResponse.class);
    }

    @Override
    public UserResponse getOne(Long id) {
        User user = this.repository.findById(id).orElseThrow(() -> new NotFoundException(messageSource.getMessage("user.exceptions.notFound", null)));
        return this.mapper.map(user, UserResponse.class);
    }

    @Override
    public UserResponse create(CreateUser dto) {
        User user = this.mapper.map(dto, User.class);
        user.setPassword(this.passwordEncoder.encode(dto.getPassword()));
        return this.mapper.map(this.repository.save(user), UserResponse.class);
    }

    @Override
    @Transactional
    public UserResponse update(Long id, UpdateUser dto) {
        User user = this.repository.findById(id).orElseThrow(() -> new NotFoundException(messageSource.getMessage("user.exceptions.notFound", null)));
        this.mapper.map(dto, user);
        this.repository.save(user);
        return this.mapper.map(user, UserResponse.class);
    }

    @Override
    public UserResponse updatePassword(Long id, String oldPassword, String newPassword) {
        User user = this.repository.findById(id).orElseThrow(() -> new NotFoundException(messageSource.getMessage("user.exceptions.notFound", null)));
        if (this.passwordEncoder.matches(oldPassword, user.getPassword())) {
            user.setPassword(this.passwordEncoder.encode(newPassword));
            return this.mapper.map(this.repository.save(user), UserResponse.class);
        }
        throw new UnauthorizedException(messageSource.getMessage("user.exceptions.passwordMismatch", null));
    }

    @Override
    public void delete(Long id) {
        this.repository.deleteById(id);
    }
}
