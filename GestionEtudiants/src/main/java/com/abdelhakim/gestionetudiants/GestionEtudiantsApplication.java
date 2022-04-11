package com.abdelhakim.gestionetudiants;

import com.abdelhakim.gestionetudiants.entities.Student;
import com.abdelhakim.gestionetudiants.enumerations.Gender;
import com.abdelhakim.gestionetudiants.repositories.StudentRepository;
import com.abdelhakim.gestionetudiants.security.entities.AppUser;
import com.abdelhakim.gestionetudiants.security.services.SecurityServiceImp;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class GestionEtudiantsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionEtudiantsApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // @Bean
    CommandLineRunner start(
            StudentRepository studentRepository,
            SecurityServiceImp securityService
    ) {
        return args -> {

            // create dummy students
            Stream.of("Amal", "Salah", "Mariam", "Abderazak").forEach(name -> {
                Student student = new Student();
                student.setFirstName(name);
                student.setLastName(name);
                student.setGender(Math.random() > 0.5 ? Gender.Female : Gender.Male);
                student.setBirthDay(new Date());
                student.setEmail(name + "@gmail.com");
                student.setStatus(true);
                studentRepository.save(student);
            });

            // create dummy roles
            Stream.of("USER", "ADMIN").forEach(name -> {
                securityService.roleCreate(name, name.equals("ADMIN") ? "Admin role" : "User role");
            });

            // create dummy users
            AppUser user1 = securityService.userCreate("mohammed", "Mohammed", "Ben Ali", "123", "123");
            securityService.addRoleToUser(user1.getUsername(), "ADMIN");
            securityService.addRoleToUser(user1.getUsername(), "USER");

            AppUser user2 = securityService.userCreate("asma", "Asma", "Abdi", "123", "123");
            securityService.addRoleToUser(user2.getUsername(), "USER");

            AppUser user3 = securityService.userCreate("zakariya", "Zakariya", "Akasis", "123", "123");
            securityService.addRoleToUser(user3.getUsername(), "USER");
        };
    }
}
