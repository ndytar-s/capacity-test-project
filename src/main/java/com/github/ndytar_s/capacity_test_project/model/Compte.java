package com.github.ndytar_s.capacity_test_project.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
public class Compte {
    @Id
    private String id;                       // "C-001"
    @ManyToOne(optional = false)                 // règle : un compte a toujours un client
    @JoinColumn(name = "client_id", nullable = false)
    private ClientBancaire client;
    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal solde;

    public Compte() {
    }

    public Compte(String id, ClientBancaire client, BigDecimal solde) {
        this.id = id;
        this.client = client;
        this.solde = solde;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ClientBancaire getClient() {
        return client;
    }

    public void setClient(ClientBancaire client) {
        this.client = client;
    }

    public BigDecimal getSolde() {
        return solde;
    }

    public void setSolde(BigDecimal solde) {
        this.solde = solde;
    }
}
