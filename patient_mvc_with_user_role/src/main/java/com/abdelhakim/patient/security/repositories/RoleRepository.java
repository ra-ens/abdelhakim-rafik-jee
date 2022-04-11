package com.abdelhakim.patient.security.repositories;

import com.abdelhakim.patient.security.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
