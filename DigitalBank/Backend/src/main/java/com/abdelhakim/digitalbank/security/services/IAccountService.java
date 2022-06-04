package com.abdelhakim.digitalbank.security.services;


import com.abdelhakim.digitalbank.security.entities.AppRole;
import com.abdelhakim.digitalbank.security.entities.AppUser;

import java.util.List;

public interface IAccountService {
    AppUser addNewUser(AppUser appUser);
    AppRole addNewRole(AppRole appRole);
    void addRoleToUser(String username, String roleName);
    AppUser loadUserByUsername(String username);
    List<AppUser> listUsers();
}
