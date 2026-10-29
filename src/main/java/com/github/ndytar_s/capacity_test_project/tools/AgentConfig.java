package com.github.ndytar_s.capacity_test_project.tools;


import com.github.ndytar.capacity.agentic.CapacityToolWrappingFactory;
import com.github.ndytar.capacity.annotation.AgentToolsSource;
import com.github.ndytar_s.capacity_test_project.data.BanqueData;
import org.springframework.ai.tool.ToolCallback;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

/**
 * CHANGEMENT : la Map<String, List<ToolCallback>> toolsParAgent remplace la
 * liste unique d'avant. Elle indexe les tools sécurisés par nom d'agent
 * ("virement", "rapports"...), consommée par AgentContextService pour
 * savoir quel jeu de tools filtrer selon l'agent visé par
 * @InjectAgentContext(agent = "...").
 *
 * Ajouter un nouvel agent = ajouter une entrée dans cette Map, rien d'autre
 * à modifier dans AgentContextService, les resolvers, ou les contrôleurs
 * existants.
 */




@Configuration
public class AgentConfig {
    @Bean
    @AgentToolsSource
    public Map<String, List<ToolCallback>> toolsParAgent1(CapacityToolWrappingFactory factory, BanqueData data) {
        return Map.of("manager", factory.wrapper(
                new CompteTools(data),
                new VirementTools(data),
                new RapportsTools(data),
                new PresoCompte(data)));
    }
    @Bean
    @AgentToolsSource
    public Map<String, List<ToolCallback>> toolsParAgent2(CapacityToolWrappingFactory factory, BanqueData data) {
        return Map.of("caissier", factory.wrapper(
                new CompteTools(data),
                new Retrait(data),
               // new Depot(data),
                new PresoCompte(data)));
    }
}
