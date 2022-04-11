package com.abdelhakim.gestionetudiants.security.repositories;

import com.abdelhakim.gestionetudiants.security.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    UserRole findByName(String roleName);
}
