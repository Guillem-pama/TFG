package com.tfg.backend.repository;

import com.tfg.backend.model.Album;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {

    List<Album> findByTitleContainingIgnoreCase(String title);

    Optional<Album> findByTitleIgnoreCase(String title);

    List<Album> findByArtist_Name(String artistName);
}
