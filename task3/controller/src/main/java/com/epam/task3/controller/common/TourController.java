package com.epam.task3.controller.common;

import com.epam.task3.entity.Tour;
import com.epam.task3.service.CountryService;
import com.epam.task3.service.HotelService;
import com.epam.task3.service.ReviewService;
import com.epam.task3.service.TourService;
import com.epam.task3.util.PaginationUtil;
import com.epam.task3.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class TourController {

    @Autowired
    private HotelService hotelService;
    @Autowired
    private CountryService countryService;

    @Autowired
    private ReviewService reviewService;
    private final TourService tourService;

    public TourController(TourService tourService) {
        this.tourService = tourService;
    }

    @GetMapping(value = "/tourList")
    public String getAllTours(@RequestParam(value = "currentPage", required = false) String currentPage,
                              Model model) {
        int maxResults = 10;
        double numPage = Math.ceil(tourService.getCountRows() / (double) maxResults);
        int page = PaginationUtil.getPage(currentPage, maxResults, numPage);

        model.addAttribute("tours", tourService.readPaginatedList(page, maxResults));
        model.addAttribute("allPages", numPage);
        model.addAttribute("currentPage", page);

        return "tourList";
    }

    @GetMapping(value = "/tour-{id}")
    public ModelAndView getTour(@PathVariable String id, Model model) {
        Tour tour = tourService.read(Validator.getNumber(id));
        if (tour != null) {
            model.addAttribute("reviews", tourService.readAllReviews(tour));
            model.addAttribute("tour", tour);
        }

        return new ModelAndView("tour");
    }
}
