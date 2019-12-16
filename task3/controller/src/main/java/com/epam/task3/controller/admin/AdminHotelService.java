package com.epam.task3.controller.admin;

import com.epam.task3.entity.Hotel;
import com.epam.task3.entity.enumerution.Feature;
import com.epam.task3.service.HotelService;
import com.epam.task3.util.PaginationUtil;
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
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
@Slf4j
public class AdminHotelService {

    @Autowired
    private HotelService hotelService;

    @GetMapping(value = "/hotels")
    public String getHotels(@RequestParam(required = false) String currentPage,
                            @RequestParam(value = "search-login", required = false) String search,
                            @RequestParam(required = false) String message,
                            Model model) {
        List<Hotel> hotels;
        int maxResults = 10;
        double numPage = 1;
        int page = 1;
        if (search != null) {
            hotels = new ArrayList<>();
        } else {
            numPage = Math.ceil(hotelService.getCountRows() / (double) maxResults);
            page = PaginationUtil.getPage(currentPage, maxResults, numPage);
            hotels = hotelService.readPaginatedList(page, maxResults);
        }

        model.addAttribute("message", message);
        model.addAttribute("hotels", hotels);
        model.addAttribute("allPages", numPage);
        model.addAttribute("currentPage", page);
        return "admin/hotels";
    }

    @GetMapping(value = {"/edit-hotel-{id}"})
    public String editHotel(@PathVariable String id, ModelMap model) {
        Hotel hotel = hotelService.read(Validator.getNumber(id));
        model.addAttribute("hotel", hotel);
        return "admin/hotel/edit";
    }

    @PostMapping(value = "/save-hotel")
    public ModelAndView saveHotel(@Valid Hotel hotel, ModelMap model) {

        log.debug("Saving hotel with id: " + hotel.getId());
        log.debug("Features: " + hotel.getFeatures());
        hotelService.create(hotel);
        model.addAttribute("message",
                "Hotel saved successfully");

        return new ModelAndView("redirect:/admin/hotels");

    }

    //TODO
    @PostMapping(value = "/update-hotel")
    public ModelAndView updateHotel(@Valid Hotel hotel, BindingResult result) {
        hotelService.update(hotel);
        return new ModelAndView("redirect:/admin/hotels");
    }

    @GetMapping(value = "/add-hotel")
    public ModelAndView addHotel(ModelMap model) {
        Hotel hotel = new Hotel();
        model.addAttribute("hotel", hotel);
        model.addAttribute("features", Feature.values());
        return new ModelAndView("admin/hotel/add");
    }

    @PostMapping(value = "/delete-hotel")
    public ModelAndView deleteHotel(@RequestParam String id, ModelMap model) {

        Long hotel_id = Validator.getNumber(id);

        if (hotel_id > 0 && hotelService.delete(hotel_id)) {
            model.addAttribute("message", "The hotel was successfully deleted!");
        } else {
            model.addAttribute("error", "The hotel can't be deleted!");
        }

        return new ModelAndView("redirect:/admin/hotels");
    }
}
