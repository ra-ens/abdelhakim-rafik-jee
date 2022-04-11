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
public class AppUser {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    @Size(min=4, max = 50)
    private String firstName;

    @Column(length = 50)
    @Size(min=4, max = 50)
    private String lastName;

    @Column(length = 50, unique = true)
    @Size(min=4, max = 50)
    private String username;

    private String password;

    @Column(unique = true)
    private String email;

    private boolean enabled;

    @ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
    private List<UserRole> roles = new ArrayList<>();
}
