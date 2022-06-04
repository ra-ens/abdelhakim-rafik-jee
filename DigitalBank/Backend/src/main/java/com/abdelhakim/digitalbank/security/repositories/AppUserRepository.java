package com.abdelhakim.digitalbank.security.repositories;


import com.abdelhakim.digitalbank.security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);
}
