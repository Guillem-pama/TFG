package com.tfg.backend.dto;

import lombok.Getter;

@Getter
public class AlbumDTO {

    private Long id;
    private String title;
    private Integer year;
    private String artistName;

    public AlbumDTO() {
    }

    public AlbumDTO(Long id, String title, Integer year, String artistName) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.artistName = artistName;
    }
}