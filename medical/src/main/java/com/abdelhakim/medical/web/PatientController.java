package com.abdelhakim.medical.web;

import com.abdelhakim.medical.entities.Patient;
import com.abdelhakim.medical.repositories.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class PatientController {

    private PatientRepository patientRepository;

    @GetMapping("/")
    public List<Patient> getPatients() {
        return patientRepository.findAll();
    }
}
