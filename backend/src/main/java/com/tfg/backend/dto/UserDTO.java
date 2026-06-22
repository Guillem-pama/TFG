package com.tfg.backend.dto;

import lombok.Getter;

@Getter
public class UserDTO {

    private Long id;
    private String name;
    private String password;
    private String permissions;

    public UserDTO() {
    }

    public UserDTO(Long id, String name, String password, String permissions) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.permissions = permissions;
    }
}