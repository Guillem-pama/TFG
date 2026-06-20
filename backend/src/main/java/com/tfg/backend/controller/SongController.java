package com.tfg.backend.controller;

import com.tfg.backend.dto.SongDTO;
import com.tfg.backend.dto.SongRequestDTO;
import com.tfg.backend.model.Song;
import com.tfg.backend.service.SongService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/songs")
public class SongController {

    private SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping
    public List<SongDTO> getAll() {
        return songService.findAll();
    }

    @PostMapping
    public Song create(@RequestBody SongRequestDTO songRequest) {
        return songService.save(songRequest);
    }

    @GetMapping("/{id}")
    public SongDTO getSongById(@PathVariable Long id) {
        return songService.getSongById(id);
    }
}