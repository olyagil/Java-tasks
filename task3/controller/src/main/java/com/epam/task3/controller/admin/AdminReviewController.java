package com.epam.task3.controller.admin;

import com.epam.task3.entity.Review;
import com.epam.task3.service.ReviewService;
import com.epam.task3.util.PaginationUtil;
import com.epam.task3.validator.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
@Slf4j
public class AdminReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping(value = "/reviews")
    public String getReviews(@RequestParam(required = false) String currentPage,
                             @RequestParam(value = "search-login", required = false) String search,
                             @RequestParam(required = false) String message,
                             @RequestParam(required = false) String error,
                             Model model) {
        List<Review> reviews;
        int maxResults = 10;
        double numPage = 1;
        int page = 1;
        if (search != null) {
            reviews = new ArrayList<>();
        } else {
            numPage = Math.ceil(reviewService.getCountRows() / (double) maxResults);
            page = PaginationUtil.getPage(currentPage, maxResults, numPage);
            reviews = reviewService.readPaginatedList(page, maxResults);
        }
        model.addAttribute("message", message);
        model.addAttribute("error", error);
        model.addAttribute("reviews", reviews);
        model.addAttribute("allPages", numPage);
        model.addAttribute("currentPage", page);

        return "admin/reviews";
    }

    @PostMapping(value = "/delete-review")
    public ModelAndView deleteReview(@RequestParam String id, ModelMap model) {

        Long review_id = Validator.getNumber(id);

        if (review_id > 0 && reviewService.delete(review_id)) {
            model.addAttribute("message", "The review was successfully deleted!");
        } else {
            model.addAttribute("error", "The review can't be deleted!");
        }

        return new ModelAndView("redirect:/admin/reviews");
    }
}
