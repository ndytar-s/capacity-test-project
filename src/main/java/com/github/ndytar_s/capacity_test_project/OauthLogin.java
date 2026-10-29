package com.github.ndytar_s.capacity_test_project;

import com.github.ndytar.capacity.aop.OauthUserInfo;
import com.github.ndytar.capacity.services.ExternalOauthVerifier;
import org.springframework.stereotype.Component;

@Component
public class OauthLogin implements ExternalOauthVerifier {
    @Override
    public OauthUserInfo verify(String rawToken) {
        /**
         * Extraire les infons avec les outils natifs de spring boot
         */
        return new OauthUserInfo("google-id-123", "manager");
    }
}
