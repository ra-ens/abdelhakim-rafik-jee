package com.abdelhakim.gestionetudiants.security.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, unique = true)
    @Size(min=4, max = 50)
    private String name;

    @Column(length = 250)
    @Size(max = 250)
    private String description;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    private List<AppUser> users = new ArrayList<>();
}
