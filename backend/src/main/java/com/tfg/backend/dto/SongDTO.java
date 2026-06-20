package com.tfg.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SongDTO {

    private Long id;
    private String title;
    private String genre;
    private Integer duration;
    private String filePath;
    private Integer releaseYear;

    private String artistName;
    private String albumTitle;

    public SongDTO() {
    }

    public SongDTO(Long id, String title, String genre, Integer duration,
            String filePath, Integer releaseYear, String artistName, String albumTitle) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.duration = duration;
        this.filePath = filePath;
        this.releaseYear = releaseYear;
        this.artistName = artistName;
        this.albumTitle = albumTitle;
    }
}