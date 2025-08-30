package org.example.shareconfig2.repository;

import org.example.shareconfig2.models.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorityRepository extends JpaRepository<Authority,Long> {
    Optional<Authority> findByName(String name);
}
