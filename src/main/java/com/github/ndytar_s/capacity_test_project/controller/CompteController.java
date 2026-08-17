package com.github.ndytar_s.capacity_test_project.controller;

import com.github.ndytar.capacity.annotation.AllowedIp;
import com.github.ndytar.capacity.annotation.OneTimeAccess;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comptes")
public class CompteController {

    @GetMapping("/liste")
    public String listerComptes() {
        return "Liste des comptes";
    }

    @GetMapping("/{id}")
    public String getCompte(@PathVariable String id) {
        return "Compte : " + id;
    }
    @GetMapping("/confirmer")
    @OneTimeAccess
    public String confirmer() {
        return "Compte information confirmer: " ;
    }

    @PostMapping
    @AllowedIp
    public String creerCompte() {
        return "Compte créé";
    }
}