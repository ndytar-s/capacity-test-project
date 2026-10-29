package com.github.ndytar_s.capacity_test_project.repository;

import com.github.ndytar.capacity.services.PoliticMappingRepository;
import com.github.ndytar_s.capacity_test_project.model.MapingPolitic;

import java.util.List;

public interface PolitiqueRepository2 extends PoliticMappingRepository<MapingPolitic> {
    List<MapingPolitic> findByRole(String role);
}