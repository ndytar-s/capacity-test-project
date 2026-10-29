package com.github.ndytar_s.capacity_test_project.repository;

import com.github.ndytar_s.capacity_test_project.model.ClientBancaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepo extends JpaRepository<ClientBancaire, Long> {}