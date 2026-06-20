package com.tfg.backend.repository;

import com.tfg.backend.model.Song;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Long> {
    
    List<Song> findByTitleContainingIgnoreCase(String title);

    List<Song> findByArtist_NameContainingIgnoreCase(String artistName);

    List<Song> findByAlbum_TitleContainingIgnoreCase(String albumName);

    boolean existsByFilePath(String filePath);

    Song findByFilePath(String filePath);    
}