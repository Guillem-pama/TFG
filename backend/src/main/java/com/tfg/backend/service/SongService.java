package com.tfg.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tfg.backend.model.Song;

import com.tfg.backend.repository.SongRepository;

import com.tfg.backend.exception.SongNotFoundException;

import com.tfg.backend.dto.SongDTO;
import com.tfg.backend.dto.SongRequestDTO;


@Service
public class SongService {

    private final SongRepository songRepository;
    private final ArtistService artistService;
    private final AlbumService albumService;
    

    public SongService(
        SongRepository songRepository,
        ArtistService artistService,
        AlbumService albumService) {
        this.songRepository = songRepository;
        this.artistService = artistService;
        this.albumService = albumService;
    }

    public List<SongDTO> findAll() {
        return songRepository.findAll().stream()
            .map(song -> convertToDTO(song))
            .toList();
    }
    
    public SongDTO getSongById(Long id) {
    
        Song song = songRepository.findById(id)
                .orElseThrow(() ->
                    new SongNotFoundException(
                        "No existe una canción con id " + id
                    )
                );
    
        return convertToDTO(song);
    }

    public SongDTO convertToDTO(Song song) {
        return new SongDTO(
                song.getId(),
                song.getTitle(),
                song.getGenre(),
                song.getDuration(),
                song.getFilePath(),
                song.getReleaseYear(),
                song.getArtist() != null ? song.getArtist().getName() : null,
                song.getAlbum() != null ? song.getAlbum().getTitle() : null
        );
    }

    public Song save(SongRequestDTO songRequest) {
        Song song = new Song();
        song.setTitle(songRequest.getTitle());
        song.setGenre(songRequest.getGenre());
        song.setDuration(songRequest.getDuration());
        song.setReleaseYear(songRequest.getYear());
        song.setFilePath(songRequest.getFilePath());

        if (songRequest.getArtistName() == null || songRequest.getArtistName().isBlank()) {
            song.setArtist(null);
        } else {
            song.setArtist(artistService.findByName(songRequest.getArtistName())
                    .orElse(artistService.create(songRequest.getArtistName())));
        }

        if (songRequest.getAlbumTitle() == null || songRequest.getAlbumTitle().isBlank()) {
            song.setAlbum(null);
        } else {
            song.setAlbum(albumService.findByTitleIgnoreCase(songRequest.getAlbumTitle())
                    .orElse(albumService.create(songRequest.getAlbumTitle(), songRequest.getYear(), song.getArtist())));
        }

        return songRepository.save(song);
    }

    public Song save(SongDTO songDTO) {
        Song song = new Song();
        song.setId(songDTO.getId());
        song.setTitle(songDTO.getTitle());
        song.setGenre(songDTO.getGenre());
        song.setDuration(songDTO.getDuration());
        song.setFilePath(songDTO.getFilePath());
        
        if (songDTO.getArtistName() == null || songDTO.getArtistName().isBlank()) {
            song.setArtist(null);
        } else {
            song.setArtist(artistService.findByName(songDTO.getArtistName())
                    .orElse(artistService.create(songDTO.getArtistName())));
        }

        if (songDTO.getAlbumTitle() == null || songDTO.getAlbumTitle().isBlank()) {
            song.setAlbum(null);
        } else {
            song.setAlbum(albumService.findByTitleIgnoreCase(songDTO.getAlbumTitle())
                    .orElse(albumService.create(songDTO.getAlbumTitle(), songDTO.getReleaseYear(), song.getArtist())));
        }

        return songRepository.save(song);
    }

    public boolean existsById(Long id) {
        return songRepository.existsById(id);
    }

    public List<SongDTO> searchByTitle(String title) {
        if (title == null || title.isBlank()) {
            return List.of();
        }
        List<Song> songs = songRepository.findByTitleContainingIgnoreCase(title);

        return songs.stream().map(this::convertToDTO).toList();
    }

    public List<SongDTO> searchByArtistName(String artistName) {
        if (artistName == null || artistName.isBlank()) {
            return List.of();
        }
        List<Song> songs = songRepository.findByArtist_NameContainingIgnoreCase(artistName);
        return songs.stream().map(this::convertToDTO).toList();
    }

    public List<SongDTO> searchByAlbumTitle(String albumTitle) {
        if (albumTitle == null || albumTitle.isBlank()) {
            return List.of();
        }
        List<Song> songs = songRepository.findByAlbum_TitleContainingIgnoreCase(albumTitle);
        return songs.stream().map(this::convertToDTO).toList();
    }

    public boolean existsByFilePath(String filePath) {
        return songRepository.existsByFilePath(filePath);
    }
}
