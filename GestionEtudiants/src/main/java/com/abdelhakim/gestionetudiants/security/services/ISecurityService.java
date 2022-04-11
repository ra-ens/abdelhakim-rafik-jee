package com.abdelhakim.gestionetudiants.security.services;

import com.abdelhakim.gestionetudiants.security.entities.AppUser;
import com.abdelhakim.gestionetudiants.security.entities.UserRole;

public interface ISecurityService {
    AppUser userCreate(String username, String firstName, String lastName, String password, String confirmPassword);
    AppUser userCreate(AppUser user, String confirmPassword);
    UserRole roleCreate(String name, String description);
    void addRoleToUser(String username, String roleName);
    void removeRoleFromUser(String username, String roleName);
    AppUser getUserByUsername(String username);
}
