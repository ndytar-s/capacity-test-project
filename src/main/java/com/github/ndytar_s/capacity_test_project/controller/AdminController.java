package com.github.ndytar_s.capacity_test_project.controller;

import com.github.ndytar.capacity.jwt_macaroons.JwtService;
import com.github.ndytar.capacity.jwt_macaroons.MacaroonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {


    @Autowired
    private MacaroonService macaroonService;


    @Autowired
    private JwtService jwtService;



    // révoquer JWT
    @DeleteMapping("/revoquer")
    public ResponseEntity<?> revoquer(@RequestHeader("X-Capacity-Token") String token) {
        return ResponseEntity.ok( jwtService.revokeJwt(token));
    }
    // révoquer all JWT
    @PostMapping("/revoquerAll")
    public ResponseEntity<?> revoquerAll() {
        return ResponseEntity.ok(  jwtService.revokeAllJwt());
    }
    // générer Macaroon
    @GetMapping("/macaroon/generer")
    public ResponseEntity<?> genererMacaroon(
            @RequestParam String ressource,
            @RequestParam List<String> actions,
            @RequestParam(defaultValue = "false") boolean oneTime,
            @RequestHeader("X-Capacity-Token") String jwtToken) {

        try {
            String macaroon = macaroonService.creer(
                    jwtToken,
                    ressource,
                    new HashSet<>(actions),
                    oneTime);
           if (macaroon == null)
               return ResponseEntity.status(401).body("Invalid token");
            return ResponseEntity.ok(macaroon);

        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode())
                    .body(e.getReason());
        }
    }

    // atténuer Macaroon
    @GetMapping("/macaroon/attenuate")
    public ResponseEntity<?> attenuate(
            @RequestParam String token,
            @RequestParam String... caveats) {

            String macaroon = macaroonService.attenuate(token, caveats);
            if (macaroon == null)
                return ResponseEntity.status(401).body("Macaroon non valide");
            return ResponseEntity.ok(macaroon);

    }

    // révoquer Macaroon
    @DeleteMapping("/macaroon/revoquer")
    public ResponseEntity<?> revoquerMacaroon(
            @RequestParam String macaroon) {

        return ResponseEntity.ok( macaroonService.revokeMacaroon(macaroon));
    }
    // révoquer All Macaroon
    @DeleteMapping("/macaroon/revoquerAll")
    public ResponseEntity<?> revoquerAllMacaroonWithJwt(
            @RequestHeader("X-Capacity-Token") String jwtToken) {
        return ResponseEntity.ok( macaroonService.revokeMacaroonsByJwt(jwtToken));
    }
}