package com.abdelhakim.patient.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = passwordEncoder();
//        String password = passwordEncoder.encode("123");
//        auth.inMemoryAuthentication()
//                .withUser("user1").password(password).roles("USER")
//                .and()
//                .withUser("user2").password(password).roles("USER")
//                .and()
//                .withUser("admin").password(password).roles("USER", "ADMIN");

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select first_name as principal, password as credentials, 'true' as enabled from user where first_name = ?")
                .authoritiesByUsernameQuery("select u.first_name as name, r.name as role from user u inner join user_roles ur on u.id = ur.users_id inner join role r on r.id = roles_id where u.first_name = ?")
                .rolePrefix("ROLE_")
                .passwordEncoder(passwordEncoder);


    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // use costume login page
        // http.formLogin().loginPage("/login");

        http.formLogin();
        http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN");
        http.authorizeRequests().antMatchers( "/user/**").hasRole("USER");
        http.authorizeRequests().antMatchers("/**").permitAll();
        http.authorizeRequests().anyRequest().authenticated();
        http.exceptionHandling().accessDeniedPage("/403");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
