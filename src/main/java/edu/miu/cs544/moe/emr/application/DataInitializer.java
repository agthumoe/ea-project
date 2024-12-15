package edu.miu.cs544.moe.emr.application;

import edu.miu.cs544.moe.emr.domain.user.UserService;
import edu.miu.cs544.moe.emr.domain.user.dto.CreateUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("data")
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private UserService userService;
    @Override
    public void run(String... args) throws Exception {
        this.userService.create(new CreateUser("Alice", "alice", "password"));
        this.userService.create(new CreateUser("Bob", "bob", "password"));
        this.userService.create(new CreateUser("Charlie", "charlie", "password"));
    }
}
