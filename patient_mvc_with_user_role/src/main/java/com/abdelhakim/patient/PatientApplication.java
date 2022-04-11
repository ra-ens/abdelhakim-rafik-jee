package com.abdelhakim.patient;

import com.abdelhakim.patient.entities.Patient;
import com.abdelhakim.patient.security.entities.User;
import com.abdelhakim.patient.enu.GenderEnum;
import com.abdelhakim.patient.repositories.PatientRepository;
import com.abdelhakim.patient.security.service.SecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class PatientApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // @Bean
    CommandLineRunner start(
            PatientRepository patientRepository,
            SecurityService securityService
    ) {
        return args -> {

            // create dummy patients
            Stream.of("Zakariya", "Soufian", "Abdelhadi", "Hajar").forEach(name -> {
                Patient patient = new Patient();
                patient.setName(name);
                patient.setGenre(Math.random() > 0.5 ? GenderEnum.male : GenderEnum.female);
                patient.setBirthDay(new Date());
                patientRepository.save(patient);
            });

            // create dummy roles
            Stream.of("USER", "ADMIN").forEach(name -> {
                securityService.roleCreate(name, name.equals("ADMIN") ? "Admin role" : "User role");
            });

            // create dummy users
            Stream.of("Mohammed", "Ali", "Firdous").forEach(name -> {
                User user = securityService.userCreate(name, name, name, "123", "123");
                securityService.addRoleToUser(user.getUsername(), Math.random() > 0.5 ? "ADMIN" : "USER");
            });
        };
    }

}
