package com.webservice.eventfye.Config.keycloak;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakConfig {
    @Value("${keycloak.serverUrl}")
    private String serverUrl;

    @Value("${keycloak.serverRealm}")
    private String serverRealm;

    @Value("${keycloak.serverClientId}")
    private String serverClientId;

    @Value("${keycloak.serverUsername}")
    private String serverUsername;

    @Value("${keycloak.serverPassword}")
    private String serverPassword;

    @Bean
    public Keycloak keycloak() {
        return KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .grantType(OAuth2Constants.PASSWORD)
                .realm(serverRealm)
                .clientId(serverClientId)
                .username(serverUsername)
                .password(serverPassword)
                .build();
    }
}
