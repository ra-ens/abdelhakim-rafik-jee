package com.abdelhakim.userroles.service;

import com.abdelhakim.userroles.entities.Role;
import com.abdelhakim.userroles.entities.User;

public interface IUserService {

    User addUser(User user);
    Role addRole(Role role);
    User findUserByUsername(String username);
    Role findRoleByName(String name);
    void addUserRole(String username, String roleName);
    User authenticate(String username, String password);
}
