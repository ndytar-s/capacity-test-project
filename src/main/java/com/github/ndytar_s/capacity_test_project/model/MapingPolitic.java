package com.github.ndytar_s.capacity_test_project.model;

import com.github.ndytar.capacity.services.MappingScopeActions;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;
@Entity
@Table(name = "MapingPolitic")
public class MapingPolitic implements MappingScopeActions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // CORRECTION : Ajouter impérativement ces annotations
    @Getter
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "politique_roles",
            joinColumns = @JoinColumn(name = "politique_id")
    )
    @Column(name = "role_name")
    private Set<String> role;

    @Getter
    @Column(nullable = false)
    private String scope;

    @Getter
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "politique_action",
            joinColumns = @JoinColumn(name = "politique_id")
    )
    @Column(name = "action")
    private Set<String> actions = new HashSet<>();

    public MapingPolitic() {}

    public MapingPolitic(String scope, Set<String> role, Set<String> actions) {
        this.role    = role;
        this.scope   = scope;
        this.actions = actions;
    }

    @Override
    public String getScopeName() {
        return getScope();
    }

    @Override
    public Set<String> getActionsSet() {
        return getActions();
    }
}
