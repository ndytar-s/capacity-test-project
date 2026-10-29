package com.github.ndytar_s.capacity_test_project.data;

import com.github.ndytar.capacity.audit.AuditCollector;
import com.github.ndytar.capacity.audit.AuditEvent;
import com.github.ndytar.capacity.audit.AuditResult;
import com.github.ndytar_s.capacity_test_project.model.Compte;
import com.github.ndytar_s.capacity_test_project.model.Virement;
import com.github.ndytar_s.capacity_test_project.repository.CompteRepo;
import com.github.ndytar_s.capacity_test_project.repository.VirementRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

/** Données de test en mémoire (FCFA). Remplace une vraie base pour les essais. */
@Slf4j
 @Component
@RequiredArgsConstructor
public class BanqueData {
    private final AuditCollector auditCollector;
    private final CompteRepo comptes; private final VirementRepo virements;

    public String solde(String compteId) {
        return comptes.findById(compteId)
                .map(c -> c.getId() + " : " + c.getSolde() + " FCFA")
                .orElse("Compte inconnu : " + compteId);
    }

    public String soldePerso(Long clientId) {
        log.info("Consultation client {}", clientId);
        var liste = comptes.findByClientId(clientId);
        return liste.isEmpty() ? "Aucun compte pour le client " + clientId
                : liste.stream().map(c -> c.getId() + " : " + c.getSolde() + " FCFA")
                .collect(Collectors.joining("\n"));
    }

    @Transactional
    public String retrait(String compteId, Long montant) {
        var opt = comptes.findById(compteId);
        if (opt.isEmpty()) return "Compte inconnu : " + compteId;
        var c = opt.get();
        BigDecimal m = BigDecimal.valueOf(montant);
        if (m.signum() <= 0) return "Montant invalide";
        if (c.getSolde().compareTo(m) < 0) {
            auditCollector.record(new AuditEvent(Instant.now(), "Retrait", "/comptes/retrait",
                    "agent caissier", AuditResult.FAILURE, "solde insuffisant"));
            return compteId + " : " + c.getSolde() + " FCFA : solde insuffisant";
        }
        c.setSolde(c.getSolde().subtract(m));
        return compteId + " : opération réussie ; montant restant : " + c.getSolde() + " FCFA";
    }

    @Transactional
    public String virer(String source, String dest, BigDecimal montant) {
        if (montant == null || montant.signum() <= 0) return "Montant invalide";
        var s = comptes.findById(source); var d = comptes.findById(dest);
        if (s.isEmpty() || d.isEmpty()) return "Compte inconnu";
        if (s.get().getSolde().compareTo(montant) < 0) return "Solde insuffisant sur " + source;
        s.get().setSolde(s.get().getSolde().subtract(montant));
        d.get().setSolde(d.get().getSolde().add(montant));
        virements.save(new Virement(LocalDate.now(), source, dest, montant));
        return "Virement de " + montant + " FCFA de " + source + " vers " + dest + " effectué";
    }

    public List<String> historique(String compteId) {
        return virements.findBySourceOrDest(compteId, compteId).stream()
                .map(v -> v.getDate() + " : " + v.getSource() + " -> " + v.getDest() + " : " + v.getMontant() + " FCFA").toList();
    }

    public String listerComptes() {
        return comptes.findAll().stream().map(c -> c.getId() + " : " + c.getSolde() + " FCFA")
                .collect(Collectors.joining("\n"));
    }

    public String rapportMensuel() {
        BigDecimal total = comptes.findAll().stream().map(Compte::getSolde).reduce(BigDecimal.ZERO, BigDecimal::add);
        return "Rapport d'octobre 2026 : " + comptes.count() + " comptes, encours total " + total
                + " FCFA, " + virements.count() + " virements enregistrés.";
    }

    @Transactional
    public String penaliser(String source, BigDecimal montant) { return virer(source, "C-001", montant); }
}