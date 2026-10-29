package com.github.ndytar_s.capacity_test_project.config;

import com.github.ndytar.capacity.login.DynamicPoliticMappingService;
import com.github.ndytar.capacity.services.CapacityPolitiqueMappingService;
import com.github.ndytar_s.capacity_test_project.repository.PolitiqueRepository;
import com.github.ndytar_s.capacity_test_project.repository.PolitiqueRepository2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Est une configuartin de dev. Un code ne sera pas inclu dans starter
@Configuration
public class ConfigPoliticMapping {
    @Autowired
    private PolitiqueRepository politiqueRepository;
    @Autowired
    private PolitiqueRepository2 politiqueRepository2;

    @Bean
    public CapacityPolitiqueMappingService capacityPolitiqueService() {
        return new DynamicPoliticMappingService(role -> politiqueRepository2.findByRole(role));
    }

}
