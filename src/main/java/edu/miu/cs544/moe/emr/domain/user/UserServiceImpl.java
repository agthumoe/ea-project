package edu.miu.cs544.moe.emr.domain.user;

import edu.miu.cs544.moe.emr.domain.user.dto.CreateUserRequest;
import edu.miu.cs544.moe.emr.domain.user.dto.UpdateUserRequest;
import edu.miu.cs544.moe.emr.domain.user.dto.UserResponse;
import edu.miu.cs544.moe.emr.exception.NotFoundException;
import edu.miu.cs544.moe.emr.exception.UnauthorizedException;
import edu.miu.cs544.moe.emr.helper.LocaleMessageProvider;
import edu.miu.cs544.moe.emr.helper.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final Mapper mapper;
    private final LocaleMessageProvider messageSource;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Page<UserResponse> getAll(Pageable pageable) {
        return this.mapper.map(this.repository.findAll(pageable), UserResponse.class);
    }

    @Override
    public UserResponse getOne(Long id) {
        User user = this.repository.findById(id).orElseThrow(() -> new NotFoundException(messageSource.getMessage("user.exceptions.notFound", null)));
        return this.mapper.map(user, UserResponse.class);
    }

    @Override
    public UserResponse create(CreateUserRequest dto) {
        User user = this.mapper.map(dto, User.class);
        user.setPassword(this.passwordEncoder.encode(dto.getPassword()));
        return this.mapper.map(this.repository.save(user), UserResponse.class);
    }

    @Override
    @Transactional
    public UserResponse update(Long id, UpdateUserRequest dto) {
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
