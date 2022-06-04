package com.abdelhakim.digitalbank.security.filters;

import com.abdelhakim.digitalbank.security.JWTConfig;
import com.abdelhakim.digitalbank.security.repositories.AppUserRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;
    private AppUserRepository appUserRepository;


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);
        return authenticationManager.authenticate(authenticationToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        System.out.println("successfulAuthentication");
        User user = (User) authResult.getPrincipal();
        Algorithm algo1 = Algorithm.HMAC256(JWTConfig.SECRET);
        String jwtAccessToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JWTConfig.EXPIRE_ACCESS_TOKEN))
                .withIssuer(request.getRequestURL().toString())
                .withClaim("roles", user.getAuthorities().stream().map(ga -> ga.getAuthority()).collect(Collectors.toList()))
                .sign(algo1);

        String jwtRefreshTokenToken = JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JWTConfig.EXPIRE_REFRESH_TOKEN))
                .withIssuer(request.getRequestURL().toString())
                .sign(algo1);
        Map<String, String> idToken = new HashMap<>();
        idToken.put("accessToken", jwtAccessToken);
        idToken.put("refreshToken", jwtRefreshTokenToken);
        idToken.put("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()).toString().replace("[", "")
                .replace("]", ""));
        if (user.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()).toString().contains("CUSTOMER")) {
            idToken.put("id", String.valueOf(appUserRepository.findByUsername(user.getUsername()).getId()));
        }
        response.setContentType("application/json");
        new ObjectMapper().writeValue(response.getOutputStream(), idToken);
    }

}
