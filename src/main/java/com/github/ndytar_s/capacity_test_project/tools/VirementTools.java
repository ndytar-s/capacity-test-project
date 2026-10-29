package com.github.ndytar_s.capacity_test_project.tools;

import com.github.ndytar.capacity.annotation.RequiresCapacity;
import com.github.ndytar_s.capacity_test_project.data.BanqueData;
import org.springframework.ai.tool.annotation.Tool;

import java.math.BigDecimal;

/*
 * resourcePattern() doit correspondre au pattern de scope réellement émis
 * dans les capacités du Manager (ex. "/virement/**"), pour que
 * CapacityDecision.resoudre(...) puisse matcher côté agentic.
 */

public class VirementTools {

    private final BanqueData data;

    public VirementTools(BanqueData data) {
        this.data = data;
    }

    @Tool(description = "Exécute un virement en FCFA entre deux comptes (identifiants comme C-001) Exiger 2 comptes differents")
    @RequiresCapacity(actions = "APPROVE", resourcePattern = "/virements/**")
    public String executerVirement(String compteSource, String compteDest, BigDecimal montant) {
        return data.virer(compteSource, compteDest, montant);
    }

    @Tool(description = "Consulte l'historique des virements d'un compte")
    @RequiresCapacity(actions = "READ", resourcePattern = "/virements/**")
    public String consulterHistorique(String compteId) {
        return String.join("\n", data.historique(compteId));
    }
}
