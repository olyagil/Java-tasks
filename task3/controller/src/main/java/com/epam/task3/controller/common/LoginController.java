package com.epam.task3.controller.common;

import com.epam.task3.entity.User;
import com.epam.task3.entity.enumerution.Roles;
import com.epam.task3.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Locale;

@Controller
@Slf4j
public class LoginController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllException(Exception e) {
        ModelAndView model = new ModelAndView("error");
        model.addObject("message", e.getMessage());
        return model;
    }

    @GetMapping(value = "/login")
    public ModelAndView login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Login or Password is wrong!!");
        }

        if (logout != null) {
            model.addObject("logout", "You've been logged out successfully.");
        }
        model.setViewName("login");

        return model;

    }

    @GetMapping(value = "/register")
    public ModelAndView addUser(ModelMap model) {
        User user = new User();
        model.addAttribute("user", user);
        return new ModelAndView("register");
    }

    @PostMapping(value = "/register")
    public ModelAndView saveUser(@Valid User user, Errors errors, ModelMap model) {
        if (errors.hasErrors()) {
            return new ModelAndView("register");
        }

        if (userService.read(user.getLogin()) != null) {
            model.addAttribute("error", "Please, enter correct data.");
            return new ModelAndView("register");
        }
        user.setRole(Roles.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.create(user);
        model.addAttribute("message",
                "User " + user.getLogin() + " registered successfully");
        return new ModelAndView("login");
    }

    @GetMapping(value = "/error")
    public String accessDenied(Model model, Principal principal) {

        if (principal != null) {
            model.addAttribute("message", "Hi " + principal.getName()
                    + "<br> You do not have permission to access this page!");
        } else {
            model.addAttribute("message",
                    "You do not have permission to access this page!");
        }
        return "error";
    }

    @GetMapping(value = "/profile")
    public String profile(Model model, Principal principal) {
        String login = principal.getName();
        model.addAttribute("loggedinuser", login);
        return "profile";
    }

    @GetMapping(value = {"/admin", "/user"})
    public String getAdmin(Model model, Principal principal) {
        return "redirect:/profile";
    }


    @GetMapping(value = {"/", "/main"})
    public String home(Locale locale, Model model, Principal principal) {
        return "main";
    }

}
