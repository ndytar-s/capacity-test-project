package com.github.ndytar_s.capacity_test_project.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class RevokedContrat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Contrat contrat;

    @Column
    private String status;

    @ElementCollection
    @Builder.Default
    private Set<String> allkp = new HashSet<>();

    @LastModifiedDate
    @Column(nullable = false)
    @Builder.Default // Indique à Lombok de conserver cette valeur par défaut
    private Instant lastModifiedDate = Instant.now();

}
