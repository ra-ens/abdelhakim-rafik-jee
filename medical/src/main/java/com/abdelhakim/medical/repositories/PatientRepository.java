package com.abdelhakim.medical.repositories;

import com.abdelhakim.medical.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    Patient findByNom(String nom);
}
