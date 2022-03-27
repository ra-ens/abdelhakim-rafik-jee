package com.abdelhakim.userroles;

import com.abdelhakim.userroles.entities.Role;
import com.abdelhakim.userroles.entities.User;
import com.abdelhakim.userroles.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class UserRolesApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserRolesApplication.class, args);
    }

    @Bean
    CommandLineRunner start(UserService userService) {
        return args -> {

            // create user
            Stream.of("Abdelhakim", "Mohammed", "Ali").forEach(name -> {
                User user = new User();
                user.setUsername(name);
                user.setPassword("qwerty123");
                userService.addUser(user);
            });

            // create roles
            Stream.of("Admin", "Responsable", "User", "SimpleUser").forEach(name -> {
                Role role = new Role();
                role.setName(name);
                userService.addRole(role);
            });

            // associate user with role
            userService.addUserRole("Abdelhakim", "Admin");
            userService.addUserRole("Abdelhakim", "Responsable");

            userService.addUserRole("Ali", "User");
            userService.addUserRole("Mohammed", "SimpleUser");

            // authenticate a user
            try {
                User user = userService.authenticate("Abdelhakim", "qwerty1234");
                System.out.println("ID: " + user.getId());
                System.out.println("USERNAME: " + user.getUsername());
                System.out.println("USER ROLES:");
                user.getRoles().forEach(role -> {
                    System.out.println("\t" + role.toString());
                });
            } catch (RuntimeException e) {
                System.out.println("[ERROR]: " + e.getMessage());
            }

        };
    }

}
