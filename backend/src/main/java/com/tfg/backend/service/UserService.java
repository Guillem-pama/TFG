package com.tfg.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.tfg.backend.model.User;
import com.tfg.backend.repository.UserRepository;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean existsByName(String name) {
        return userRepository.existsByName(name);
    }

    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }

    public User findByUsername(String username) {
        return userRepository.findByName(username)
                .orElse(null);
    }

    public User registerUser(String name, String password) {
        if(existsByName(name)) {
            throw new RuntimeException("El usuario ya existe");
        }

        User newUser = new User();
        newUser.setName(name);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setPermissions("USER");

        return userRepository.save(newUser);
    }

    public User validateUser(String name, String password) {
        User user = userRepository.findByName(name)
                .orElse(null);

        if (user == null) {
            throw new RuntimeException("Usuario no encontrado");

        }

        if(passwordEncoder.matches(
                password,
                user.getPassword()
        )) {
            return user;
        }

        throw new RuntimeException("Contraseña incorrecta");
    }
    
}
