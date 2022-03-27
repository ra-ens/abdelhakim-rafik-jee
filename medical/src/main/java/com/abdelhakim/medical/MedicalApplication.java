package com.abdelhakim.medical;

import com.abdelhakim.medical.entities.Consultation;
import com.abdelhakim.medical.entities.Medecin;
import com.abdelhakim.medical.entities.Patient;
import com.abdelhakim.medical.entities.RendezVous;
import com.abdelhakim.medical.repositories.ConsultationRepository;
import com.abdelhakim.medical.repositories.MedecinRepository;
import com.abdelhakim.medical.repositories.PatientRepository;
import com.abdelhakim.medical.repositories.RendezVousRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class MedicalApplication {

    public static void main(String[] args) {
        SpringApplication.run(MedicalApplication.class, args);
    }

    @Bean
    CommandLineRunner start(
            PatientRepository patientRepository,
            MedecinRepository medecinRepository,
            RendezVousRepository rendezVousRepository,
            ConsultationRepository consultationRepository
    ) {
        return args -> {

            // create patients
            Stream.of("Mohammed", "Abderazak", "Mariam", "Ali").forEach(name -> {
                Patient patient = new Patient();
                patient.setNom(name);
                patient.setDateNaissance(new Date());
                patient.setMalade(false);
                patientRepository.save(patient);
            });

            String[] specialities = {"Andrologie", "Cardiologie", "Chirurgie", "Anesthésiologie", "Gynécologie"};

            // create Medecines
            Stream.of("Ben Hamo", "Jazoli", "Firdous", "Saad").forEach(name -> {
                Medecin medecin = new Medecin();
                medecin.setNom(name);
                medecin.setEmail(name + "@gmail.com");
                medecin.setSpecialite(specialities[(int) (Math.floor(Math.random() * 5))]);
                medecinRepository.save(medecin);
            });

            // create rendez-vous
            Patient patient = patientRepository.findByNom("Mohammed");
            Medecin medecin = medecinRepository.findByNom("Firdous");

            RendezVous rendezVous = new RendezVous();
            rendezVous.setDate(new Date());
            rendezVous.setPatient(patient);
            rendezVous.setMedecin(medecin);
            rendezVousRepository.save(rendezVous);

            // create consultation
            RendezVous rendezVous1 = rendezVousRepository.findById(1L).orElse(null);
            Consultation consultation = new Consultation();
            consultation.setDate(new Date());
            consultation.setRapport("Consultation rapport");
            consultation.setRendezVous(rendezVous1);
            consultationRepository.save(consultation);
        };
    }

}
