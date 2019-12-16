package com.epam.task3.controller.admin;

import com.epam.task3.entity.User;
import com.epam.task3.service.UserService;
import com.epam.task3.util.PaginationUtil;
import com.epam.task3.validator.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
@Slf4j
public class AdminUserController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserService userService;

    @GetMapping(value = "/users")
    public String getUsers(@RequestParam(required = false) String currentPage,
                           @RequestParam(value = "search-login", required = false) String search,
                           @RequestParam(required = false) String message,
                           @RequestParam(required = false) String error,
                           Model model) {
        List<User> users;
        int maxResults = 10;
        double numPage = 1;
        int page = 1;
        if (search != null) {
            users = new ArrayList<>();
            User user = userService.read(search);
            if (user != null) {
                users.add(user);
            }
        } else {

            numPage = Math.ceil(userService.getCountRows() / (double) maxResults);
            page = PaginationUtil.getPage(currentPage, maxResults, numPage);
            users = userService.readPaginatedList(page, maxResults);
        }

        model.addAttribute("message", message);
        model.addAttribute("error", error);
        model.addAttribute("users", users);
        model.addAttribute("allPages", numPage);
        model.addAttribute("currentPage", page);
        return "admin/users";
    }

    @GetMapping(value = {"/edit-user-{id}"})
    public String editUser(@PathVariable String id, ModelMap model) {
        User user = userService.read(Long.valueOf(id));
        model.addAttribute("user", user);
        return "admin/user/edit";
    }

    @PostMapping(value = "/save-user")
    public ModelAndView saveUser(@Valid User user, ModelMap model) {

        if (userService.read(user.getLogin()) != null) {
            model.addAttribute("error", "Such user is already exist.");
            return new ModelAndView("redirect:/admin/add-user");
        }
        if (!Validator.checkLogin(user.getLogin())) {
            model.addAttribute("error",
                    "Please, enter correct data. At list 6 digits,"
                            + " must ends with a letter or digit and starts "
                            + "with a letter.");
            return new ModelAndView("redirect:/admin/add-user");
        }

        if (!Validator.checkPassword(user.getPassword())) {
            model.addAttribute("error",
                    "Password must be at list 8 digits,"
                            + " must contain at least 1 lowercase and uppercase and 1 digit");
            return new ModelAndView("redirect:/admin/add-user");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.create(user);
        model.addAttribute("message",
                "User " + user.getLogin() + " is registered successfully");

        return new ModelAndView("redirect:/admin/users");

    }

    @PostMapping(value = "/update-user")
    public ModelAndView updateUser(@Valid User user, BindingResult result,
                                   ModelMap model, @RequestParam String id) {
        user.setPassword(userService.read(Long.valueOf(id)).getPassword());
        userService.update(user);
        model.addAttribute("user", user);
        model.addAttribute("message", "User is successfully changed!");
        return new ModelAndView("admin/user/edit");
    }

    @GetMapping(value = "/add-user")
    public ModelAndView addUser(ModelMap model,
                                @RequestParam(name = "error", required = false) String error) {
        if (error != null) {
            model.addAttribute("error", error);
        }
        User user = new User();
        model.addAttribute("user", user);
        return new ModelAndView("admin/user/add");
    }

    @PostMapping(value = "/delete-user")
    public ModelAndView deleteUser(@RequestParam String id, Principal principal,
                                   ModelMap model) {

        Long user_id = Validator.getNumber(id);
        User user = userService.read(user_id);

        if (!user.getLogin().equals(principal.getName())) {
            userService.delete(user_id);
            model.addAttribute("message", "User is successfully deleted!");
        } else {
            model.addAttribute("error", "You can't delete yourself");
        }
        return new ModelAndView("redirect:/admin/users");
    }
}
