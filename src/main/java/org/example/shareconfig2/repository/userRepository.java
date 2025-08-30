package org.example.shareconfig2.repository;

import org.example.shareconfig2.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface userRepository  extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
