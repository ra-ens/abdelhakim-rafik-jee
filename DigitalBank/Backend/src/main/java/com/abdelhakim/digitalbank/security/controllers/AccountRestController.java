package com.abdelhakim.digitalbank.security.controllers;

import com.abdelhakim.digitalbank.security.JWTConfig;
import com.abdelhakim.digitalbank.security.entities.AppRole;
import com.abdelhakim.digitalbank.security.entities.AppUser;
import com.abdelhakim.digitalbank.security.services.IAccountService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
public class AccountRestController {

    private IAccountService accountService;

    @GetMapping(path = "/users")
    @PostAuthorize("hasAuthority('ADMIN')")
    public List<AppUser> appUsers() {
        return accountService.listUsers();
    }

    @PostMapping(path = "/users")
    @PostAuthorize("hasAuthority('ADMIN')")
    public AppUser saveUser(@RequestBody AppUser appUser) {
        return accountService.addNewUser(appUser);
    }

    @PostMapping(path = "/roles")
    public AppRole saveRole(@RequestBody AppRole appRole) {
        return accountService.addNewRole(appRole);
    }

    @PostMapping(path = "/addRoleToUser")
    public void addRoleToUser(@RequestBody RoleUserForm roleUserForm) {
        accountService.addRoleToUser(roleUserForm.getUsername(), roleUserForm.getRoleName());
    }

    @GetMapping(path = "/refreshToken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String auhToken = request.getHeader(JWTConfig.AUTH_HEADER);
        if (auhToken != null && auhToken.startsWith(JWTConfig.PREFIX)) {
            try {
                String jwt = auhToken.substring(JWTConfig.PREFIX.length());
                Algorithm algorithm = Algorithm.HMAC256(JWTConfig.SECRET);
                JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = jwtVerifier.verify(jwt);
                String username = decodedJWT.getSubject();
                AppUser appuser = accountService.loadUserByUsername(username);
                String jwtAccessToken = JWT.create()
                        .withSubject(appuser.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + JWTConfig.EXPIRE_ACCESS_TOKEN))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("roles", appuser.getAppRoles().stream().map(ga -> ga.getRoleName()).collect(Collectors.toList()))
                        .sign(algorithm);
                Map<String, String> idToken = new HashMap<>();
                idToken.put("accessToken", jwtAccessToken);
                idToken.put("refreshToken", jwt);
                idToken.put("roles", appuser.getAppRoles().stream().map(ga -> ga.getRoleName())
                        .collect(Collectors.toList()).toString().replace("[", "")
                        .replace("]", ""));
                if(appuser.getAppRoles().stream().map(AppRole::getRoleName)
                        .collect(Collectors.toList()).toString().contains("CUSTOMER")){
                    idToken.put("id", appuser.getId().toString());
                }
                response.setContentType("application/json");
                new ObjectMapper().writeValue(response.getOutputStream(), idToken);

            } catch (Exception e) {
                throw e;
            }
        } else {
            throw new RuntimeException("Refresh token required !!!");
        }
    }

    @GetMapping(path = "/profile")
    public AppUser profile(Principal principal) {
        return accountService.loadUserByUsername(principal.getName());
    }

    @Data
    class RoleUserForm {
        private String username;
        private String roleName;
    }
}
