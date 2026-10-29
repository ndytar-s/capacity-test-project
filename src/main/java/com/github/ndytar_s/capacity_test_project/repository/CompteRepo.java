package com.github.ndytar_s.capacity_test_project.repository;

import com.github.ndytar_s.capacity_test_project.model.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompteRepo extends JpaRepository<Compte, String> {
    List<Compte> findByClientId(Long clientId);
}
