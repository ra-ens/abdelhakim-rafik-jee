package com.abdelhakim.gestionetudiants.web;

import com.abdelhakim.gestionetudiants.entities.Student;
import com.abdelhakim.gestionetudiants.security.entities.AppUser;
import com.abdelhakim.gestionetudiants.security.services.SecurityServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
public class SecurityController {

    SecurityServiceImp securityService;

    @GetMapping("/login")
    public String login() {
        return "security/login";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        AppUser user = new AppUser();
        model.addAttribute("user", user);
        return "security/signup";
    }

    @PostMapping("/user/save")
    public String saveUser(@Valid AppUser user, String confirmPassword, BindingResult bindingResult) {
//        securityService.userCreate(user, "1233");
        System.out.println(">>>" + confirmPassword);
        System.out.println(">>>" + user.getFirstName());
        return "/login";
    }
}
