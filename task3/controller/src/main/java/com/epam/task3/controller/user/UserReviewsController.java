package com.epam.task3.controller.user;

import com.epam.task3.entity.Review;
import com.epam.task3.service.ReviewService;
import com.epam.task3.service.TourService;
import com.epam.task3.service.UserService;
import com.epam.task3.validator.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserReviewsController {

    @Autowired
    private UserService userService;
    @Autowired
    private TourService tourService;
    @Autowired
    private ReviewService reviewService;

    @GetMapping(value = "reviews")
    public ModelAndView getReviewsByUser(@RequestParam(required = false) String message,
                                         Model model, Principal principal) {
        List<Review> reviews = userService
                .readAllReviews(userService.read(principal.getName()));
        model.addAttribute("message", message);
        model.addAttribute("reviews", reviews);
        return new ModelAndView("user/reviews");
    }

    @PostMapping(value = "delete-review")
    public ModelAndView deleteReview(@RequestParam String id) {
        reviewService.delete(Validator.getNumber(id));
        return new ModelAndView("redirect:/user/reviews",
                "message", "The review was successfully deleted!");
    }

    @PostMapping(value = "update-review")
    public ModelAndView updateUser(@Valid Review review,
                                   @RequestParam String tour_id,
                                   @RequestParam String review_id,
                                   BindingResult result,
                                   ModelMap model,
                                   Principal principal) {

        review.setId(Validator.getNumber(review_id));
        review.setDate(new Date());
        review.setUser(userService.read(principal.getName()));
        review.setTour(tourService.read(Validator.getNumber(tour_id)));

        if (result.hasErrors()) {
            model.addAttribute("error", "Please, enter correct data!");
        }

        log.info("REVIEW: " + review);
        reviewService.update(review);
        model.addAttribute("review", review);
        model.addAttribute("message", "Review was successfully changed!");
        return new ModelAndView("user/review/edit");
    }

    @PostMapping(value = "edit-review")
    public ModelAndView editReview(@RequestParam String review_id,
                                   @RequestParam(required = false) String message,
                                   @RequestParam(required = false) String error,
                                   ModelMap model) {

        model.addAttribute("message", message);
        model.addAttribute("error", error);
        Review review = reviewService.read(Validator.getNumber(review_id));
        return new ModelAndView("user/review/edit", "review",
                review);
    }

    @PostMapping(value = "add-review")
    public ModelAndView addReview(@RequestParam String id, ModelMap modelMap) {

        modelMap.addAttribute("tour",
                tourService.read(Validator.getNumber(id)));
        modelMap.addAttribute("review", new Review());
        return new ModelAndView("user/review/add");
    }

    @PostMapping(value = "save-review")
    public ModelAndView saveReview(@Valid Review review,
                                   @RequestParam String tour_id,
                                   BindingResult result, Principal principal) {
        review.setDate(new Date());
        review.setUser(userService.read(principal.getName()));
        review.setTour(tourService.read(Validator.getNumber(tour_id)));

        if (result.hasErrors()) {
            new ModelAndView("redirect:/user/add-review");
        }
        reviewService.create(review);
        return new ModelAndView("redirect:/user/reviews", "message", "The review was successfully added!");
    }
}
