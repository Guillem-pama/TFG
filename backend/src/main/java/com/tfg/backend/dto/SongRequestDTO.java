package com.tfg.backend.dto;

import lombok.Getter;

@Getter
public class SongRequestDTO {

    private String title;
    private String genre;
    private String filePath;
    private int duration;
    private Integer year;
    

    private String artistName;
    private String albumTitle;
}