package com.tfg.backend.service;

import org.springframework.stereotype.Service;

import java.util.Optional;

import com.tfg.backend.model.Album;
import com.tfg.backend.model.Artist;

import com.tfg.backend.repository.AlbumRepository;

@Service
public class AlbumService {

    private final AlbumRepository albumRepository;

    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public Optional<Album> findByTitleIgnoreCase(String title) {
        return albumRepository.findByTitleIgnoreCase(title);
    }

    public Album create(String name, Integer year, Artist artist) {
        Album album = new Album(
            name,
            year,
            artist
        );
        albumRepository.save(album);
        return album;
    }
    
}
