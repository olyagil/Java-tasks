package com.epam.task3.controller.common;

import com.epam.task3.entity.Tour;
import com.epam.task3.entity.User;
import com.epam.task3.service.UserService;
import com.epam.task3.validator.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
@RequestMapping("/")
@Slf4j
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/user-{id}")
    public ModelAndView getTour(@PathVariable String id, Model model) {

        User user = userService.read(Validator.getNumber(id));
        if (user != null) {
            model.addAttribute("reviews", userService.readAllReviews(user));
            model.addAttribute("tours", userService.readAllTours(user));
            model.addAttribute("user", user);
        }
        return new ModelAndView("user");
    }

}
