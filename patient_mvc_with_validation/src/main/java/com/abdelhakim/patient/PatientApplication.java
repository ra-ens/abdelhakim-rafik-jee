package com.abdelhakim.patient;

import com.abdelhakim.patient.entities.Patient;
import com.abdelhakim.patient.entities.Role;
import com.abdelhakim.patient.entities.User;
import com.abdelhakim.patient.enu.GenderEnum;
import com.abdelhakim.patient.repositories.PatientRepository;
import com.abdelhakim.patient.repositories.RoleRepository;
import com.abdelhakim.patient.repositories.UserRepository;
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

    // @Bean
    CommandLineRunner start(
            PatientRepository patientRepository,
            UserRepository userRepository,
            RoleRepository roleRepository
    ) {
        return args -> {
            PasswordEncoder encoder = new BCryptPasswordEncoder();

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
                Role role = new Role();
                role.setName(name);
                roleRepository.save(role);
            });

            // create dummy users
            Stream.of("Mohammed", "Ali", "Firdous").forEach(name -> {
                User user = new User();
                user.setFirstName(name);
                user.setLastName(name);
                user.setPassword(encoder.encode("123"));

                Role role = roleRepository.findByName(Math.random() > 0.5 ? "ADMIN" : "USER");
                user.getRoles().add(role);
                role.getUsers().add(user);
                userRepository.save(user);
            });
        };
    }

}
