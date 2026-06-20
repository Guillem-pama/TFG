package com.tfg.backend.controller;

import com.tfg.backend.dto.SongDTO;
import com.tfg.backend.service.SongService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/search")
public class SearchController {
    
    @Autowired
    private SongService songService;

    @GetMapping("/songs")
    public List<SongDTO> searchSongs(@RequestParam String query) {
        return songService.searchByTitle(query);
    }

    @GetMapping("/songsbyartist")
    public List<SongDTO> searchArtists(@RequestParam String query) {
        return songService.searchByArtistName(query);
    }

    @GetMapping("/songsbyalbum")
    public List<SongDTO> searchAlbums(@RequestParam String query) {
        return songService.searchByAlbumTitle(query);
    }
    
}