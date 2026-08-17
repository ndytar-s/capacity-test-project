package com.github.ndytar_s.capacity_test_project.repository;

import com.github.ndytar_s.capacity_test_project.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUsername(String username);
}

