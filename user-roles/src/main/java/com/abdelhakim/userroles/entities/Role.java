package com.abdelhakim.userroles.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Role {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, length = 25)
    private String name;
    @ToString.Exclude
    @ManyToMany(fetch = FetchType.EAGER)
    private List<User> users = new ArrayList<>();
}
