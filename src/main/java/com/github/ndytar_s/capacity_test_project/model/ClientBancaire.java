package com.github.ndytar_s.capacity_test_project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ClientBancaire {
    @Id
    private Long id;
    private String nom;

    public ClientBancaire() {
    }

    public ClientBancaire(Long id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
