package com.github.ndytar_s.capacity_test_project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @GetMapping
    public String listerTransactions() {
        return "Liste des transactions";
    }

    @PostMapping
    public String creerTransaction() {
        return "Transaction créée";
    }
}