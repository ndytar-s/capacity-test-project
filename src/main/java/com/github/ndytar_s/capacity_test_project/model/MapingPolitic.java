package com.github.ndytar_s.capacity_test_project.model;

import com.github.ndytar.capacity.capacityModel.AbstractPoliticMapping;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Set;

@Entity
@Table(name = "MapingPolitic")
public class MapingPolitic extends AbstractPoliticMapping {
    public MapingPolitic() {}
    public MapingPolitic(String scope, Set<String> role, Set<String> actions) {
        super(scope, role, actions);
    }
}
