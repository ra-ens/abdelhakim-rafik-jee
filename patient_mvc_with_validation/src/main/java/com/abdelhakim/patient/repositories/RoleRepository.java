package com.abdelhakim.patient.repositories;

import com.abdelhakim.patient.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
