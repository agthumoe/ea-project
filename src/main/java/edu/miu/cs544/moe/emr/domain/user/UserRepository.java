package edu.miu.cs544.moe.emr.domain.user;

import edu.miu.cs544.moe.emr.shared.repository.CoreRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends CoreRepository<User> {
    Optional<User> findByUsernameIgnoreCase(String username);
}
