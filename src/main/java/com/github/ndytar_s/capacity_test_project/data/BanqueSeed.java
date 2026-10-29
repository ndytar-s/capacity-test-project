package com.github.ndytar_s.capacity_test_project.data;

import com.github.ndytar_s.capacity_test_project.model.ClientBancaire;
import com.github.ndytar_s.capacity_test_project.model.Compte;
import com.github.ndytar_s.capacity_test_project.model.Virement;
import com.github.ndytar_s.capacity_test_project.repository.ClientRepo;
import com.github.ndytar_s.capacity_test_project.repository.CompteRepo;
import com.github.ndytar_s.capacity_test_project.repository.VirementRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class BanqueSeed implements CommandLineRunner {
    private final ClientRepo clients; private final CompteRepo comptes; private final VirementRepo virements;

    @Override public void run(String... a) {
        var c1 = clients.save(new ClientBancaire(1L, "Fatou Diop"));
        var c2 = clients.save(new ClientBancaire(2L, "Moussa Ndiaye"));
        var c3 = clients.save(new ClientBancaire(3L, "Awa Sow"));
        comptes.save(new Compte("C-001", c1, new BigDecimal("250000")));
        comptes.save(new Compte("C-002", c2, new BigDecimal("1200000")));
        comptes.save(new Compte("C-003", c3, new BigDecimal("80000")));
        virements.save(new Virement(LocalDate.of(2026, 9, 28), "C-001", "C-002", new BigDecimal("50000")));
        virements.save(new Virement(LocalDate.of(2026, 10, 2), "C-002", "C-003", new BigDecimal("120000")));
    }
}
