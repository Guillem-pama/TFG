package com.tfg.backend.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "songs")
@Getter
@Setter
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

    @ManyToOne(optional = true)
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @ManyToOne(optional = true)
    @JoinColumn(name = "album_id")
    private Album album;

    @JsonIgnore
    @ManyToMany(mappedBy = "songs")
    private List<PlayList> playlists;
}