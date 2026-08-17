package com.github.ndytar_s.capacity_test_project.config;

import com.github.ndytar.capacity.auth.CapacityAuthManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static com.github.ndytar.capacity.config.CapacitySecurityConfigurer.capacitySecurity;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain configure(HttpSecurity http, CapacityAuthManager capacity) throws Exception {
        http.with(capacitySecurity(), Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/admin/**").permitAll()
                        .requestMatchers("/auth/**").permitAll()
                        .anyRequest().access(capacity));

        return http.build();


    }
}
