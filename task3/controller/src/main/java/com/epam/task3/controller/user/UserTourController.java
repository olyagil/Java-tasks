package com.epam.task3.controller.user;

import com.epam.task3.entity.Tour;
import com.epam.task3.entity.User;
import com.epam.task3.service.TourService;
import com.epam.task3.service.UserService;
import com.epam.task3.validator.Validator;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserTourController {

    @Autowired
    private UserService userService;
    @Autowired
    private TourService tourService;

    @GetMapping(value = "/tours")
    public String getToursByUsers(@RequestParam(required = false) String message,
                                  Model model, Principal principal) {
        User user = userService.read(principal.getName());
        List<Tour> tours = userService.readAllTours(user);
        model.addAttribute("tours", tours);
        model.addAttribute("message", message);
        return "user/tours";
    }

    @PostMapping(value = "/order-tour")
//    @Transactional
    public ModelAndView orderTour(@RequestParam String id, Principal principal) {
        User user = userService.read(principal.getName());
//        Hibernate.initialize(user.getTours());
        Tour tour = tourService.read(Validator.getNumber(id));
        tour.getUsers().add(user);
        return new ModelAndView("redirect:/user/tours",
                "message", "Tour is successfully add");
    }
}
