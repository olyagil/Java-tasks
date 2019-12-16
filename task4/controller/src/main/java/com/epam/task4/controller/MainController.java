package com.epam.task4.controller;

import com.epam.task4.entity.User;
import com.epam.task4.entity.enumerution.Roles;
import com.epam.task4.repository.UserRepository;
import com.epam.task4.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
public class MainController {

    private final UserService service;

    private final PasswordEncoder passwordEncoder;

    public MainController(UserService service, PasswordEncoder passwordEncoder) {
        this.service = service;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping({"/", "/agency"})
    public String main() {
        return "Welcome!";
    }

    @GetMapping("/agency/signup")
    public String getSignupPage() {
        return "Signup";
    }

    @PostMapping("/agency/signup")
    public void signup(@RequestParam String login, @RequestParam String password) {
        service.save(new User(null, login, passwordEncoder.encode(password), Roles.USER));
    }

    @PostMapping("/validate-login")
    public Boolean emailExists(@RequestParam String login) {
        return service.existsByLogin(login);
    }
}
