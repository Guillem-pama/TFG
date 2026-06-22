package com.tfg.backend.controller;

import com.tfg.backend.dto.AuthResponseDTO;
import com.tfg.backend.dto.UserRequestDTO;
import com.tfg.backend.model.User;
import com.tfg.backend.service.JwtService;
import com.tfg.backend.service.UserService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final JwtService jwtService;

    public AuthController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody UserRequestDTO request) {

        User user = userService.validateUser(
                request.getUsername(),
                request.getPassword()
        );

        String token = jwtService.generateToken(user.getName());

        AuthResponseDTO response = new AuthResponseDTO(
                token,
                user.getName(),
                user.getPermissions()
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@RequestBody UserRequestDTO request) {

        User user = userService.registerUser(
                request.getUsername(),
                request.getPassword()
        );

        String token = jwtService.generateToken(user.getName());

        AuthResponseDTO response = new AuthResponseDTO(
                token,
                user.getName(),
                user.getPermissions()
        );

        return ResponseEntity.ok(response);
    }
}