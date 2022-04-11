package com.abdelhakim.gestionetudiants.security.repositories;

import com.abdelhakim.gestionetudiants.security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    AppUser findByUsername(String username);
}
