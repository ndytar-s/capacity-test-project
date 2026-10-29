package com.github.ndytar_s.capacity_test_project.repository;


import com.github.ndytar_s.capacity_test_project.model.Contrat;
import com.github.ndytar_s.capacity_test_project.model.RevokedContrat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnuleContReopsitory extends JpaRepository<RevokedContrat, Integer> {
    RevokedContrat findByContrat(Contrat contrat);
}
