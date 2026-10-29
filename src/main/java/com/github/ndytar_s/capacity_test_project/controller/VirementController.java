package com.github.ndytar_s.capacity_test_project.controller;

import com.github.ndytar.capacity.annotation.RequiresCapacity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/virements")
public class VirementController {

    @GetMapping
    public String listerVirements() {
        return "Liste des virements";
    }

    @PostMapping("/approuver/{id}")
    @RequiresCapacity(actions = "APPROVE")
    public String approuver(@PathVariable String id) {
        return "Virement approuvé : " + id;
    }
}
