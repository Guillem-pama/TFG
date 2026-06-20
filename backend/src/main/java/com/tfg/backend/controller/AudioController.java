package com.tfg.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;

import com.tfg.backend.service.AudioService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.core.io.Resource;

import org.springframework.http.ResponseEntity;

@RestController
public class AudioController {

    @Autowired
    private AudioService audioService;

    @GetMapping("/Audio")
    public ResponseEntity<Resource> getAudio(@RequestParam Long id) {
        return audioService.getAudioResource(id);
    }
}