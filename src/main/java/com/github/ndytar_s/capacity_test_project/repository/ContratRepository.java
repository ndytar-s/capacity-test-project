package com.github.ndytar_s.capacity_test_project.repository;

import com.github.ndytar_s.capacity_test_project.model.Contrat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;

public interface ContratRepository extends JpaRepository<Contrat, Long> {
     Contrat findByNumber(String number);
     Optional<Contrat> findFirstByOrderByIdDesc();
     Optional<Contrat> findByAllkp(String kp);
     @Query("SELECT DISTINCT k FROM Contrat c JOIN c.allkp k")
     Set<String> findAllUniqueKp();

}
