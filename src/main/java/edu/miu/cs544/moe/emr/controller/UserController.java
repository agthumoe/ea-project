package edu.miu.cs544.moe.emr.controller;

import edu.miu.cs544.moe.emr.model.User;
import edu.miu.cs544.moe.emr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return this.userService.getAllUsers();
    }

    @GetMapping("{id}")
    public User getOne(@PathVariable Long id) {
        return this.userService.getOneUser(id).orElseThrow(() -> new RuntimeException("Not found"));
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return this.userService.create(user);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        this.userService.deleteById(id);
    }
}
