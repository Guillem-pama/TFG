package com.tfg.backend.model;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "songs")
@Data
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private Integer duration;
    
    @Column(unique = true, nullable = false)
    private String filePath;
    
    private Integer releaseYear;
    private String genre;

    @ManyToOne (optional = true)
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @ManyToOne (optional = true)
    @JoinColumn(name = "album_id")
    private Album album;    

    @ManyToMany(mappedBy = "songs")
    private List<PlayList> playlists;
}