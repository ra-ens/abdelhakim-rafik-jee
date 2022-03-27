package com.abdelhakim.medical.repositories;

import com.abdelhakim.medical.entities.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedecinRepository extends JpaRepository<Medecin, Long> {

    Medecin findByNom(String nom);
}
