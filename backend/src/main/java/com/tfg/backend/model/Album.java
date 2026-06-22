package com.tfg.backend.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "albums")
@Getter
@Setter
public class Album {
    protected Album() {}

    public Album(String title, Integer year, Artist artist) {
        this.title = title;
        this.year = year;
        this.artist = artist;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private Integer year;

    @ManyToOne
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @JsonIgnore
    @OneToMany(mappedBy = "album")
    private List<Song> songs;
}