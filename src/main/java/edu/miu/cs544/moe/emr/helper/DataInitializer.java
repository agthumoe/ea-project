package edu.miu.cs544.moe.emr.helper;

import edu.miu.cs544.moe.emr.domain.user.Role;
import edu.miu.cs544.moe.emr.domain.user.User;
import edu.miu.cs544.moe.emr.domain.user.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Profile("data")
@Component
public class DataInitializer implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        logger.info("Initializing data...");
        this.userRepository.save(new User("Administrator", "alice", passwordEncoder.encode("password"), Role.ROLE_ADMIN));
        this.userRepository.save(new User("Medical Practitioner", "bob", passwordEncoder.encode("password"), Role.ROLE_PRACTITIONER));
        this.userRepository.save(new User("Nurse", "casey", passwordEncoder.encode("password"), Role.ROLE_NURSE));
    }
}
