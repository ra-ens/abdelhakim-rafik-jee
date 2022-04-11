package com.abdelhakim.patient.web;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class SecurityController {

    @GetMapping(name = "login", path = "/login")
    public String login() {
        return "login.html";
    }
}
