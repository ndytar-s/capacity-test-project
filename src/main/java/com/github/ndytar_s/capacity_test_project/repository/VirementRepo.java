package com.github.ndytar_s.capacity_test_project.repository;

import com.github.ndytar_s.capacity_test_project.model.Virement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VirementRepo extends JpaRepository<Virement, Long> {
    List<Virement> findBySourceOrDest(String source, String dest);
}
