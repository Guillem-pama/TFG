package com.tfg.backend.controller;

import com.tfg.backend.dto.ScanLibraryRequestDTO;
import com.tfg.backend.service.LibraryScannerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Scanner")
public class LibraryScannerController {

    @Autowired
    private LibraryScannerService libraryScannerService;

    @PostMapping
    public void scanLibrary(@RequestBody ScanLibraryRequestDTO request) {
        libraryScannerService.scanLibrary(request.getPath());
    }
    
}
