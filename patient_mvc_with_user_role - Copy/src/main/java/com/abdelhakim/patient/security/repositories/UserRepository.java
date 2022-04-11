package com.abdelhakim.patient.security.repositories;

import com.abdelhakim.patient.security.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
