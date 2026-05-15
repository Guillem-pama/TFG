package com.tfg.backend.controller;

import com.tfg.backend.model.Song;
import com.tfg.backend.repository.SongRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/songs")
public class SongController {

    private final SongRepository repository;

    public SongController(SongRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Song> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public Song create(@RequestBody Song song) {
        return repository.save(song);
    }
}