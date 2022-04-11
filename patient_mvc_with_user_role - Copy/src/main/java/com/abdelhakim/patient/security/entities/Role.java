package com.abdelhakim.patient.security.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Role {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private String description;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<User> users = new ArrayList<>();

    @Override
    public String toString() {
        return "Role{ =" + id + ", name=" + name + "}";
    }
}
