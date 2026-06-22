package com.tfg.backend.dto;

public class AuthResponseDTO {

    private String token;
    private String username;
    private String permissions;

    public AuthResponseDTO(String token, String username, String permissions) {
        this.token = token;
        this.username = username;
        this.permissions = permissions;
    }

    public String getToken() {
        return token;
    }

    public String getUsername() {
        return username;
    }

    public String getPermissions() {
        return permissions;
    }
}