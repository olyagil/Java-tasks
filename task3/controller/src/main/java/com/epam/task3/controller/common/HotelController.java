package com.epam.task3.controller.common;


import com.epam.task3.entity.Hotel;
import com.epam.task3.service.HotelService;
import com.epam.task3.util.PaginationUtil;
import com.epam.task3.validator.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
@Slf4j
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @GetMapping(value = "/hotelList")
    public ModelAndView getHotels(@RequestParam(required = false) String currentPage,
                                  Model model) {

        int maxResults = 10;

        double numPage = Math.ceil(hotelService.getCountRows() / (double) maxResults);
        int page = PaginationUtil.getPage(currentPage, maxResults, numPage);

        model.addAttribute("hotels", hotelService.readPaginatedList(page, maxResults));
        model.addAttribute("allPages", numPage);
        model.addAttribute("currentPage", page);

        return new ModelAndView("hotelList");
    }

    @GetMapping(value = "/hotel-{id}")
    public ModelAndView getTour(@PathVariable String id, ModelMap model) {

        Hotel hotel = hotelService.read(Validator.getNumber(id));
        if (hotel != null) {
            model.addAttribute("hotel", hotel);
        }
        return new ModelAndView("hotel");
    }
}
