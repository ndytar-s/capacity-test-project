package com.github.ndytar_s.capacity_test_project.data;

import com.github.ndytar_s.capacity_test_project.model.MapingPolitic;
import com.github.ndytar_s.capacity_test_project.model.Users;
import com.github.ndytar_s.capacity_test_project.repository.PolitiqueRepository;
import com.github.ndytar_s.capacity_test_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PolitiqueRepository politiqueRepository;

//    @Override
//    public void run(String... args) throws Exception {
//
//    }

    @Override
    public void run(String... args) {

        // utilisateurs
        userRepository.saveAll(List.of(
                new Users("client1",      "pass123", Set.of("CLIENT")),
                new Users("client2",      "pass123", Set.of("CLIENT")),
                new Users("client3",      "pass123", Set.of("CLIENT")),
                new Users("manager",     "pass123", Set.of("MANAGER")),
                new Users("controleur",  "pass123", Set.of("CONTROLEUR")),
                new Users("caissier",    "pass123", Set.of("CAISSIER")),

                new Users("super_admin", "pass123", Set.of("MANAGER", "CONTROLEUR","JOKER")) // Multi-rôle
        ));

        politiqueRepository.saveAll(List.of(
                new MapingPolitic("/comptes/**",      Set.of("MANAGER"),             Set.of("READ", "WRITE")),
                new MapingPolitic("/comptes/**",      Set.of("CAISSIER", "CLIENT"),  Set.of("READ")),
                new MapingPolitic("/comptes/perso/{sub}",      Set.of("CAISSIER", "CLIENT","MANAGER","CONTROLEUR"),  Set.of("READ","WRITE")),
                new MapingPolitic("/virements/**",    Set.of("MANAGER"),             Set.of("READ", "APPROVE")),
                new MapingPolitic("/virements/**",    Set.of("JOKER"),               Set.of("*")),
                new MapingPolitic("/rapports/**",     Set.of("MANAGER", "CONTROLEUR"),Set.of("READ", "EXPORT")),
                new MapingPolitic("/audits/**",       Set.of("CONTROLEUR"),          Set.of("READ")),
                new MapingPolitic("/transactions/**", Set.of("CAISSIER"),            Set.of("READ", "WRITE")),
                new MapingPolitic("/releves/**",      Set.of("CLIENT"),              Set.of("READ", "EXPORT"))
        ));
   }
}
