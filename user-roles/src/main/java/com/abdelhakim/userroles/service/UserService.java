package com.abdelhakim.userroles.service;

import com.abdelhakim.userroles.entities.Role;
import com.abdelhakim.userroles.entities.User;
import com.abdelhakim.userroles.repositories.RoleRepository;
import com.abdelhakim.userroles.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class UserService implements IUserService{

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public Role findRoleByName(String name) {
        return roleRepository.findByName(name);
    }

    @Override
    public void addUserRole(String username, String roleName) {
        User user = this.findUserByUsername(username);
        Role role = this.findRoleByName(roleName);
        user.getRoles().add(role);
        role.getUsers().add(user);
    }

    @Override
    public User authenticate(String username, String password) {
        User user = findUserByUsername(username);
        // user not found or incorrect password
        if(user == null || !user.getPassword().equals(password))
            throw new RuntimeException("Username or password is incorrect");
        // user authenticated successfully
        return user;
    }
}
