package com.epam.task3.controller.common;

import com.epam.task3.service.ReviewService;
import com.epam.task3.util.PaginationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping(value = "/reviewList")
    public String getReviews(@RequestParam(value = "currentPage", required = false) String currentPage,
                             Model model) {
        int maxResults = 10;

        double numPage = Math.ceil(reviewService.getCountRows() / (double) maxResults);
        int page = PaginationUtil.getPage(currentPage, maxResults, numPage);
        model.addAttribute("reviews", reviewService.readPaginatedList(page, maxResults));
        model.addAttribute("allPages", numPage);
        model.addAttribute("currentPage", page);

        return "reviewList";
    }

}
