package edu.miu.cs544.moe.emr.domain.user;

import edu.miu.cs544.moe.emr.domain.user.dto.UserRequest;
import edu.miu.cs544.moe.emr.domain.user.dto.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserResponse> getAllUsers() {
        return this.userService.getAllUsers();
    }

    @GetMapping("{id}")
    public UserResponse getOne(@PathVariable Long id) {
        return this.userService.getOneUser(id).orElseThrow(() -> new RuntimeException("Not found"));
    }

    @PostMapping
    public UserResponse create(@RequestBody UserRequest user) {
        return this.userService.create(user);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        this.userService.deleteById(id);
    }
}
