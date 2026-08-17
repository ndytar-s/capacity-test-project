package com.github.ndytar_s.capacity_test_project.controller;

import com.github.ndytar.capacity.annotation.RequiresCapacity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/releves")
public class ReleveController {

    @GetMapping("/{id}")
    public String getReleve(@PathVariable String id) {
        return "Relevé : " + id;
    }

    @GetMapping("/exporter/{id}")
    @RequiresCapacity(actions = {"EXPORT"})
    public String exporterReleve(@PathVariable String id) {
        return "Relevé exporté : " + id;
    }
}
