package com.abdelhakim.medical.repositories;

import com.abdelhakim.medical.entities.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
}
