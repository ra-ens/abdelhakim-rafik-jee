package com.abdelhakim.patient.repositories;

import com.abdelhakim.patient.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
