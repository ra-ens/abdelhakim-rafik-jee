package com.abdelhakim.patient.security.service;

import com.abdelhakim.patient.security.entities.Role;
import com.abdelhakim.patient.security.entities.User;
import com.abdelhakim.patient.security.repositories.RoleRepository;
import com.abdelhakim.patient.security.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class SecurityService implements ISecurityService{

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public User userCreate(String username, String firstName, String lastName, String password, String confirmPassword) {
        if(!password.equals(confirmPassword)) throw new RuntimeException("Passwords doesn't match");
        // hash the password
        String hashedPassword = passwordEncoder.encode(password);
        // create user
        User user = new User();
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(hashedPassword);
        user.setEnabled(true);
        return userRepository.save(user);
    }

    @Override
    public Role roleCreate(String name, String description) {
        Role role = new Role();
        role.setName(name);
        role.setDescription(description);
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        User user = userRepository.findByUsername(username);
        if(user == null) throw new RuntimeException("User not found");
        Role role = roleRepository.findByName(roleName);
        if( role == null) throw new RuntimeException("Role not found");
        // associate role to user
        user.getRoles().add(role);
        role.getUsers().add(user);
    }

    @Override
    public void removeRoleFromUser(String username, String roleName) {
        User user = userRepository.findByUsername(username);
        if(user == null) throw new RuntimeException("User not found");
        Role role = roleRepository.findByName(roleName);
        if( role == null) throw new RuntimeException("Role not found");
        // associate role to user
        user.getRoles().remove(role);
        role.getUsers().remove(user);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
