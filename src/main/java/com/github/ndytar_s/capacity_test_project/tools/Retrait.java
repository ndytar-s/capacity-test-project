package com.github.ndytar_s.capacity_test_project.tools;

import com.github.ndytar.capacity.annotation.RequiresCapacity;
import com.github.ndytar_s.capacity_test_project.data.BanqueData;
import org.springframework.ai.tool.annotation.Tool;

public class Retrait {
    private final BanqueData data;
    public Retrait(BanqueData data) {
        this.data = data;
    }

    @Tool(description = "Effectuer un retait d'un seul compte en utilisant l'id compte et mont à retirer")
    @RequiresCapacity(actions = "WRITE", resourcePattern = "/comptes/**")
   public String retrait(String compteId, Long montant) {
      return data.retrait(compteId, montant);
   }
}
