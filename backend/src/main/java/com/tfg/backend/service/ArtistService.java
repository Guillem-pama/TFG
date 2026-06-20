package com.tfg.backend.service;

import com.tfg.backend.model.Artist;

import com.tfg.backend.repository.ArtistRepository;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class ArtistService {

    private final ArtistRepository artistRepository;

    public ArtistService(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    public Optional<Artist> findByName(String name) {
        return artistRepository.findByNameIgnoreCase(name);
    }

    public Artist create(String name) {
        Artist artist = new Artist();
        artist.setName(name);
        artistRepository.save(artist);
        return artist;
    }

}
