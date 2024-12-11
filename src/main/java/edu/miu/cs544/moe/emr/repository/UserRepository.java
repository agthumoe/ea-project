package edu.miu.cs544.moe.emr.repository;

import edu.miu.cs544.moe.emr.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
