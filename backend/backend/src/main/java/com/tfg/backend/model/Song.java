package com.tfg.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private Integer duration;
    private String filePath;
    private Integer releaseYear;
    private String genre;

    @ManyToOne
    @JoinColumn(name = "artist_id", nullable = false)
    private Artist artist;

}