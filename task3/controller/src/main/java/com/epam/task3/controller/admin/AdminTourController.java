package com.epam.task3.controller.admin;

import com.epam.task3.dao.util.SearchParameter;
import com.epam.task3.entity.Tour;
import com.epam.task3.entity.enumerution.TourType;
import com.epam.task3.service.CountryService;
import com.epam.task3.service.HotelService;
import com.epam.task3.service.TourService;
import com.epam.task3.util.PaginationUtil;
import com.epam.task3.validator.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.annotation.MultipartConfig;
import javax.validation.Valid;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/admin")
@Slf4j
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class AdminTourController {

    @Autowired
    private TourService tourService;
    @Autowired
    private CountryService countryService;
    @Autowired
    private HotelService hotelService;

    @GetMapping(value = "/tours")
    public String getTours(@RequestParam(required = false) String currentPage,
                           @RequestParam(required = false) String message,
                           Model model) {

        int maxResults = 10;
        double numPage = Math.ceil(tourService.getCountRows() / (double) maxResults);
        int page = PaginationUtil.getPage(currentPage, maxResults, numPage);

        if (message != null) {
            model.addAttribute("message", message);
        }
        model.addAttribute("tours", tourService.readPaginatedList(page, maxResults));
        model.addAttribute("allPages", numPage);
        model.addAttribute("currentPage", page);
        return "admin/tours";
    }

    @GetMapping(value = "/find-tours")
    public String findTours(@RequestParam(required = false) String tourType,
                            Model model) {

        SearchParameter type = SearchParameter.TOUR_TYPE;
        List<Tour> tours = new ArrayList<>();
        try {
            type.setValue(TourType.valueOf(tourType.toUpperCase().replaceAll(" ", "_")));
            tours = tourService.findByCriteria(Arrays.asList(type));
            model.addAttribute("allPages", tours.size());
        } catch (IllegalArgumentException e) {
            log.error("No such tour type", e);
        }
        model.addAttribute("tours", tours);
        return "admin/tours";
    }

    @GetMapping(value = {"/edit-tour-{id}"})
    public String editTour(@PathVariable String id,
                           @RequestParam(name = "message", required = false) String message,
                           @RequestParam(name = "error", required = false) String error,
                           ModelMap model) {

        model.addAttribute("tour", tourService.read(Validator.getNumber(id)));
        model.addAttribute("types", TourType.values());
        model.addAttribute("countries", countryService.read());
        model.addAttribute("hotels", hotelService.read());
        model.addAttribute("message", message);
        model.addAttribute("error", error);
        return "admin/tour/edit";
    }

    @PostMapping(value = "/save-tour")
    public ModelAndView saveTour(@Valid Tour tour, ModelMap model,
                                 @RequestParam String country_id,
                                 @RequestParam String hotel_id,
                                 @RequestParam(name = "img") MultipartFile file,
                                 BindingResult result) {
        String photo;
        try {
            photo = Validator.getPhoto(file);
        } catch (IOException e) {
            log.error("Can't save photo", e);
            return new ModelAndView("redirect:admin/add-tour", "error", "Can't save tour");
        }

        tour.setCountry(countryService.read(Validator.getNumber(country_id)));
        tour.setHotel(hotelService.read(Validator.getNumber(hotel_id)));
        tour.setPhoto(photo);
        if (result.hasErrors()) {
            new ModelAndView("redirect:admin/add-tour", "error", "Error saving tour");
        }

        tourService.create(tour);
        model.addAttribute("message", "Tour is successfully created");
        model.addAttribute("id", tour.getId());

        return new ModelAndView("redirect:/admin/edit-tour-{id}");
    }

    @PostMapping(value = "/update-tour")
    public ModelAndView updateTour(@Valid Tour tour,
                                   @RequestParam String country_id,
                                   @RequestParam String hotel_id,
                                   @RequestParam String tour_id,
                                   @RequestParam String tour_cost,
                                   BindingResult result,
                                   ModelMap model) {
        tour.setId(Validator.getNumber(tour_id));
        tour.setCountry(countryService.read(Validator.getNumber(country_id)));
        tour.setHotel(hotelService.read(Validator.getNumber(hotel_id)));
        tour.setPhoto(tourService.read(Validator.getNumber(tour_id)).getPhoto());
        tour.setCost(BigDecimal.valueOf(Double.parseDouble(tour_cost.replaceAll(",", ""))));
        log.info("Tour: " + tour);
        if (result.hasErrors()) {
            model.addAttribute("error", "Please enter correct data.");
        } else {
            tourService.update(tour);
            model.addAttribute("message", "Tour is successfully changed!");
        }
        model.addAttribute("id", tour.getId());
        return new ModelAndView("redirect:/admin/edit-tour-{id}");
    }

    @GetMapping(value = "/add-tour")
    public ModelAndView addTour(ModelMap model) {

        model.addAttribute("types", TourType.values());
        model.addAttribute("tour", new Tour());

        model.addAttribute("countries", countryService.read());
        model.addAttribute("hotels", hotelService.read());
        return new ModelAndView("admin/tour/add");
    }

    @PostMapping(value = "/delete-tour")
    public ModelAndView deleteTour(@RequestParam String id, ModelMap model) {
        if (tourService.delete(Validator.getNumber(id))) {
            model.addAttribute("message", "Tour is successfully deleted!");
        } else {
            model.addAttribute("error", "Can't delete tour!");
        }
        return new ModelAndView("redirect:/admin/tours");
    }
}
