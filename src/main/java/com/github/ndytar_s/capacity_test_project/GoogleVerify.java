package com.github.ndytar_s.capacity_test_project;

import com.github.ndytar.capacity.aop.OauthUserInfo;
import com.github.ndytar.capacity.services.ExternalOauthVerifier;
import org.springframework.stereotype.Component;

@Component
public class GoogleVerify implements ExternalOauthVerifier {
    @Override
    public OauthUserInfo verify(String rawToken) {
        // Code du dev : Appel API Google, décodage, etc.
        // Le probleme: comment extraire user info depauis le token tiers sans clé secrete?
        return new OauthUserInfo("google-id-123", "manager");
    }
}
