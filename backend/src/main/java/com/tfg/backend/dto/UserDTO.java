package com.tfg.backend.dto;

import lombok.Getter;

@Getter
public class UserDTO {

    private Long id;
    private String name;
    private String permissions;

    public UserDTO() {
    }

    public UserDTO(Long id, String name, String permissions) {
        this.id = id;
        this.name = name;
        this.permissions = permissions;
    }
}