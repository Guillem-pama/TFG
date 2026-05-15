package com.tfg.backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Artist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String bio;

}
