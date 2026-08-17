package com.github.ndytar_s.capacity_test_project.controller;
import com.github.ndytar.capacity.annotation.*;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/documents")
public class DocumentController {

    @GetMapping("/rapport")
    // scope déduit : /documents/**, action déduite : READ
    public String rapport(@AuthenticationPrincipal String token) {
        return "rapport accessible avec Token: " + token;
    }

    @GetMapping("/facture")
    // scope déduit : /documents/**, action déduite : READ
    public String facture(@AuthenticationPrincipal String token) {
        return "facture accessible avec Token: " + token;
    }

    @PostMapping("/approuver")
    @RequiresCapacity(actions = {"APPROVE"})  // action métier explicite
    public String approuver() {
        return "document approuvé";
    }

    @GetMapping("/export")
    @RequiresCapacity(actions = {"EXPORT"})
    @OneTimeAccess
    public String exporter() {
        return "document exporté";
    }
}