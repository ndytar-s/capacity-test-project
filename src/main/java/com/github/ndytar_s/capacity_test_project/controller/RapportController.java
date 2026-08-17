package com.github.ndytar_s.capacity_test_project.controller;

import com.github.ndytar.capacity.annotation.OneTimeAccess;
import com.github.ndytar.capacity.annotation.RequiresCapacity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rapports")
public class RapportController {

    @GetMapping
    public String listerRapports() {
        return "Liste des rapports";
    }

    @GetMapping("/mensuel")
    public String getRapportMensuel() {
        return "Rapport mensuel";
    }

    @GetMapping("/exporter")
    @RequiresCapacity(actions = {"EXPORT"})
    @OneTimeAccess
    public String exporter() {
        return "Rapport exporté";
    }
}