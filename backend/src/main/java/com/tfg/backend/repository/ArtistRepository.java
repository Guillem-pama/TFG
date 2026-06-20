package com.tfg.backend.repository;

import com.tfg.backend.model.Artist;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
    List<Artist> findByNameContainingIgnoreCase(String name);

    Optional<Artist> findByNameIgnoreCase(String name);
}
