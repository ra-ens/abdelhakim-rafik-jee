package com.abdelhakim.patient.security.service;

import com.abdelhakim.patient.security.entities.Role;
import com.abdelhakim.patient.security.entities.User;

public interface ISecurityService {

    User userCreate(String username, String firstName, String lastName, String password, String confirmPassword);
    Role roleCreate(String name, String description);
    void addRoleToUser(String username, String roleName);
    void removeRoleFromUser(String username, String roleName);
    User getUserByUsername(String username);
}
