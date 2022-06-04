package com.abdelhakim.digitalbank.security.repositories;


import com.abdelhakim.digitalbank.security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole, Long> {
    AppRole findByRoleName (String roleName);
}
