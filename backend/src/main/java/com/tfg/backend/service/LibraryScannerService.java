package com.tfg.backend.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import com.tfg.backend.dto.SongDTO;
import com.tfg.backend.service.SongService;

import com.tfg.backend.config.ConfigService;
import com.tfg.backend.config.AppConfig;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class LibraryScannerService {

    @Autowired
    private SongService songService;

    @Autowired
    private ConfigService configService;

    public void scanLibrary(String folderPath) {
    
        Path root = Paths.get(folderPath);

        AppConfig config = configService.loadConfig();

        if(config.getLibraryPath() != folderPath) {
            config.setLibraryPath(folderPath);
            configService.saveConfig(config);
        }

        try {
            Files.walk(root)
                    .filter(Files::isRegularFile)
                    .filter(this::isAudioFile)
                    .forEach(this::processFile);

        } catch (IOException e) {
            throw new RuntimeException("Error scanning library", e);
        }
    }

    private boolean isAudioFile(Path path) {
    
        String fileName = path.toString().toLowerCase();

        return fileName.endsWith(".mp3")
                || fileName.endsWith(".flac")
                || fileName.endsWith(".wav")
                || fileName.endsWith(".ogg");
    }

    private void processFile(Path path) {

        String filePath = path.toAbsolutePath().toString();

        if (songService.existsByFilePath(filePath)) {
            return;
        }

        SongDTO song = new SongDTO();

        try {
            AudioFile audioFile = AudioFileIO.read(path.toFile());
           
            Tag tag = audioFile.getTag();
    
            String title = tag.getFirst((FieldKey.TITLE));
            String artist = tag.getFirst(FieldKey.ARTIST);
            String album = tag.getFirst(FieldKey.ALBUM);
            String year = tag.getFirst(FieldKey.YEAR);
            String genre = tag.getFirst(FieldKey.GENRE);
    
            int duration = audioFile.getAudioHeader().getTrackLength();

            if (title == null || title.isBlank()) {
                String fileName = path.getFileName().toString();
                int dot = fileName.lastIndexOf('.');
                if (dot > 0) {
                    fileName = fileName.substring(0, dot);
                }
                title = fileName;
            }
            
            song.setTitle(cleanMetadata(title));
            song.setFilePath(filePath);
            song.setReleaseYear(parseYear(year));
            song.setArtistName(cleanMetadata(artist));
            song.setAlbumTitle(cleanMetadata(album));
            song.setGenre(cleanMetadata(genre));
            song.setDuration(duration);
        
            songService.save(song);
        
            System.out.println("Canción añadida: " + filePath);
            
        } catch (Exception e) {
            System.err.println("Error procesando archivo: " + filePath);
            e.printStackTrace();
        }
    }

    private String cleanMetadata(String value) {
    
        if (value == null || value.isBlank()) {
            return null;
        }
    
        return value.trim();
    }
    
    private Integer parseYear(String year) {

        System.out.println("Año:" + year);
    
        if (year == null || year.isBlank()) {
            return null;
        }
    
        try {
            return Integer.parseInt(year);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}