package com.github.ndytar_s.capacity_test_project.tools;




import com.github.ndytar.capacity.annotation.RequiresCapacity;
import com.github.ndytar_s.capacity_test_project.data.BanqueData;
import org.springframework.ai.tool.annotation.Tool;

import java.math.BigDecimal;


public class CompteTools {

    private final BanqueData data;

    public CompteTools(BanqueData data) {
        this.data = data;
    }

    @Tool(description = "Donne uniquement le solde d'un compte à partir de son identifiant (ex. C-001)")
    @RequiresCapacity(actions = "READ", resourcePattern = "/comptes/**")
    public String consulterSolde(String compteId) {
        return data.solde(compteId);
    }

    @Tool(description = "Liste tous les comptes avec leur solde")
    @RequiresCapacity(actions = "READ", resourcePattern = "/comptes/**")
    public String listerComptes() {
        return data.listerComptes();
    }

    @Tool(description = "Pénaliser un compte 'x' d'un montant à se renseigner. Le BENEFICIER est : C-001")
    @RequiresCapacity(actions = "WRITE", resourcePattern = "/comptes/**")
    public String penaliser(String source, BigDecimal montant) {
        return data.penaliser(source, montant);
    }
}

