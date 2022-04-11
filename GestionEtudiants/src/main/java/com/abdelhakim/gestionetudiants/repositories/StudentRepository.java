package com.abdelhakim.gestionetudiants.repositories;

import com.abdelhakim.gestionetudiants.entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

    Page<Student> findByFirstNameContainsOrLastNameContains(String firstName, String lastName, Pageable pageable);
}
