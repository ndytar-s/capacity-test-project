package com.github.ndytar_s.capacity_test_project.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
@Entity
@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
public class Contrat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;
    private String description;
    private int nbPresta;
    private String status;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String doc;

    private int originalPageCount;

    @ElementCollection
    @Builder.Default
    private Set<String> allkp = new HashSet<>();

    @ElementCollection
    private Set<String> allmail;

    @LastModifiedDate
    @Column(nullable = false)
    @Builder.Default // Indique à Lombok de conserver cette valeur par défaut
    private Instant lastModifiedDate = Instant.now();

}
