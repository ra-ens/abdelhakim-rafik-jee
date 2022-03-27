package com.abdelhakim.userroles.repositories;

import com.abdelhakim.userroles.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
