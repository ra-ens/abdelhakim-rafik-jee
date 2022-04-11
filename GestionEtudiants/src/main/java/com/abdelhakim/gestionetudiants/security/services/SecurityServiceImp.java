package com.abdelhakim.gestionetudiants.security.services;

import com.abdelhakim.gestionetudiants.security.entities.AppUser;
import com.abdelhakim.gestionetudiants.security.entities.UserRole;
import com.abdelhakim.gestionetudiants.security.repositories.AppUserRepository;
import com.abdelhakim.gestionetudiants.security.repositories.UserRoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class SecurityServiceImp implements ISecurityService{

    private AppUserRepository userRepository;
    private UserRoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public AppUser userCreate(String username, String firstName, String lastName, String password, String confirmPassword) {
        if(!password.equals(confirmPassword)) throw new RuntimeException("Passwords doesn't match");
        // hash the password
        String hashedPassword = passwordEncoder.encode(password);
        // create user
        AppUser user = new AppUser();
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(hashedPassword);
        user.setEnabled(true);
        return userRepository.save(user);
    }

    public AppUser userCreate(AppUser user, String confirmPassword) {
        if(!user.getPassword().equals(confirmPassword)) throw new RuntimeException("Passwords doesn't match");
        // hash the password
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        user.setEnabled(true);
        return userRepository.save(user);
    }

    @Override
    public UserRole roleCreate(String name, String description) {
        UserRole role = new UserRole();
        role.setName(name);
        role.setDescription(description);
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser user = userRepository.findByUsername(username);
        if(user == null) throw new RuntimeException("User not found");
        UserRole role = roleRepository.findByName(roleName);
        if( role == null) throw new RuntimeException("Role not found");
        // associate role to user
        user.getRoles().add(role);
        role.getUsers().add(user);
    }

    @Override
    public void removeRoleFromUser(String username, String roleName) {
        AppUser user = userRepository.findByUsername(username);
        if(user == null) throw new RuntimeException("User not found");
        UserRole role = roleRepository.findByName(roleName);
        if( role == null) throw new RuntimeException("Role not found");
        // associate role to user
        user.getRoles().remove(role);
        role.getUsers().remove(user);
    }

    @Override
    public AppUser getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
