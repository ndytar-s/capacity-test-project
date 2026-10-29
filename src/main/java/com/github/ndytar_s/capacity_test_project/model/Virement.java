package com.github.ndytar_s.capacity_test_project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Virement {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDate date;
    private String source;
    private String dest;
    private BigDecimal montant;

    public Virement() {
    }

    public Virement(LocalDate date, String source, String dest, BigDecimal montant) {
        this.date = date;
        this.source = source;
        this.dest = dest;
        this.montant = montant;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }
}