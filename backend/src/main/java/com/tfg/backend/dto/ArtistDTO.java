package com.tfg.backend.dto;

import lombok.Getter;

@Getter
public class ArtistDTO {

    private Long id;
    private String name;

    public ArtistDTO() {
    }

    public ArtistDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}