package com.github.ndytar_s.capacity_test_project.tools;

import com.github.ndytar.capacity.annotation.RequiresCapacity;
import com.github.ndytar_s.capacity_test_project.data.BanqueData;
import org.springframework.ai.tool.annotation.Tool;

public class RapportsTools {

    private final BanqueData data;

    public RapportsTools(BanqueData data) {
        this.data = data;
    }

    @Tool(description = "Génère et affiche le rapport mensuel de synthèse des comptes uniquement")
    @RequiresCapacity(actions = "READ", resourcePattern = "/rapports/**")
    public String genererRapportMensuel() {
        return data.rapportMensuel();
    }

    @Tool(description = "Exporte le rapport mensuel vers un fichier et donne le nom du fichier aucune d'autres opération")
    @RequiresCapacity(actions = "EXPORT", resourcePattern = "/rapports/**")
    public String exporterRapport() {
        return "Rapport exporté : rapport-2026-10.csv";
    }
}