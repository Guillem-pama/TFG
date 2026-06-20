package com.tfg.backend.model;

import java.util.List;
import java.util.Set;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String password;
    private String permissions;

    @OneToMany(mappedBy = "creator")
    private List<PlayList> createdPlaylists;

    @ManyToMany(mappedBy = "allowedUsers")
    private Set<PlayList> accessiblePlaylists;
}
