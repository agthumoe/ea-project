package edu.miu.cs544.moe.emr.domain.user;

import edu.miu.cs544.moe.emr.domain.user.dto.CreateUserRequest;
import edu.miu.cs544.moe.emr.domain.user.dto.UpdateUserRequest;
import edu.miu.cs544.moe.emr.domain.user.dto.UserResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse create(@RequestBody @Validated CreateUserRequest user) {
        return this.userService.create(user);
    }

    @GetMapping
    public Page<UserResponse> getAllUsers(Pageable pageable) {
        return this.userService.getAll(pageable);
    }

    @GetMapping("{id}")
    public UserResponse getOne(@PathVariable Long id) {
        return this.userService.getOne(id);
    }

    @PutMapping("{id}")
    public UserResponse update(@PathVariable Long id, @RequestBody @Validated UpdateUserRequest user) {
        return this.userService.update(id, user);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        this.userService.delete(id);
    }
}
