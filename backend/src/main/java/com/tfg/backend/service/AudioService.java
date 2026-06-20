package com.tfg.backend.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.net.MalformedURLException;

import org.springframework.stereotype.Service;

import com.tfg.backend.service.SongService;
import com.tfg.backend.dto.SongDTO;

import java.nio.file.Path;
import java.nio.file.Paths;


@Service
public class AudioService {
    @Autowired
    private SongService songService;
    
    public ResponseEntity<Resource> getAudioResource(long id) {

        SongDTO song = songService.getSongById(id);

        try {
            Path filePath = Paths.get(song.getFilePath());
            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists() || !resource.isReadable()) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_TYPE, "audio/mpeg")
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + song.getTitle() + "\"")
                    .body(resource);

        } catch (MalformedURLException e) {
            return ResponseEntity.badRequest().build();
        }
        
    }

}