package com.github.ndytar_s.capacity_test_project.tools;

import com.github.ndytar.capacity.annotation.RequiresCapacity;
import com.github.ndytar.capacity.annotation.SubjectId;
import com.github.ndytar_s.capacity_test_project.data.BanqueData;
import org.springframework.ai.tool.annotation.Tool;

public class PresoCompte {

    private final BanqueData data;

    public PresoCompte(BanqueData data) {
        this.data = data;
    }

    @Tool(description = "Donne le solde d'un compte à partir de son identifiant personnel)")
    @RequiresCapacity(actions = "READ", resourcePattern = "/comptes/perso/**")
    public String consulterSoldePreso(@SubjectId Long id) {
        return data.soldePerso(id);
    }
}
