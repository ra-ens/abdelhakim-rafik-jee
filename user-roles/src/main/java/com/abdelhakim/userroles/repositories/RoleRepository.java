package com.abdelhakim.userroles.repositories;

import com.abdelhakim.userroles.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}