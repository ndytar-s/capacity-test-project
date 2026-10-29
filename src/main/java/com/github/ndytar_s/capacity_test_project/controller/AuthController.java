package com.github.ndytar_s.capacity_test_project.controller;

import com.github.ndytar.capacity.jwt_macaroons.LoginRequest;
import com.github.ndytar.capacity.jwt_macaroons.TokenResponse;
import com.github.ndytar.capacity.login.AuthService;
import com.github.ndytar.capacity.annotation.*;
import com.github.ndytar.capacity.services.IAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tools.jackson.databind.ObjectMapper;
import tools.jackson.databind.jsonFormatVisitors.JsonValueFormat;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private static final Logger log = LoggerFactory.getLogger(AuthController.class);
    @Autowired
    private IAuthService authService;
    @Autowired ObjectMapper objectMapper;
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
            return ResponseEntity.ok(authService.login(
                    request.getUsername(),
                    request.getPassword(),
                    objectMapper.writeValueAsString(request.getJwk())
            ));

    }

        @GetMapping("/loginOauth")
        @CapacityOauth // 1. L'AOP s'exécute, vérifie le token tiers, génère le TokenResponse
        public ResponseEntity<?> logOAuth(TokenResponse token) {// 2. Injecté par oap
       if (token == null) {
           log.warn("\n //token is null");
           return ResponseEntity.status(401).body("token is null");
       }
        log.info("Le token {}", token.getAccessTokens());
        return ResponseEntity.ok(token);
        }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(
            @RequestHeader("X-Refresh-Token") String refreshToken) {
        try {
            return ResponseEntity.ok(authService.refresh(refreshToken));
        } catch (RuntimeException e) {
            log.warn("Échec refresh : {}", e.getMessage());
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }


}