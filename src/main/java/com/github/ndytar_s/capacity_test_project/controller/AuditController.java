package com.github.ndytar_s.capacity_test_project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/audits")
public class AuditController {

    @GetMapping
    public String listerAudits() {
        return "Liste des audits";
    }

    @GetMapping("/transactions")
    public String auditerTransactions() {
        return "Audit des transactions";
    }
}
