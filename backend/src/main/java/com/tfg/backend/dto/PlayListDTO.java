package com.tfg.backend.dto;

import lombok.Getter;

@Getter
public class PlayListDTO {

    private Long id;
    private String name;
    private String description;
    private Long creatorId;
    private String creatorName;
    private int songCount;

    public PlayListDTO() {
    }

    public PlayListDTO(Long id, String name, String description, Long creatorId, String creatorName, int songCount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.creatorId = creatorId;
        this.creatorName = creatorName;
        this.songCount = songCount;
    }
}