package com.webservice.eventfye.Service;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class KeycloakService {
    @Value("${keycloak.retrieveUserRealm}")
    private String REALM_NAME;

    private final Keycloak keycloak;

    public KeycloakService(Keycloak keycloak){
        this.keycloak = keycloak;
    }

    public UserRepresentation findById(UUID id){
        try{
            return keycloak.realm(REALM_NAME).users().get(id.toString()).toRepresentation();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao encontrar usuário! Id de entrada: " + id.toString());
        }
    }
}
