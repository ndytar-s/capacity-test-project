package com.github.ndytar_s.capacity_test_project.repository;

import com.github.ndytar_s.capacity_test_project.model.MapingPolitic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PolitiqueRepository extends JpaRepository<MapingPolitic, Long> {
    List<MapingPolitic> findByRole(String role);
}