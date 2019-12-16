package com.epam.task4.controller;

import com.epam.task4.entity.Tour;
import com.epam.task4.service.TourService;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/agency/tours")
public class TourController {

    private final TourService service;

    public TourController(TourService service) {
        this.service = service;
    }

    @GetMapping(value = "/")
    public List<Tour> getAllTours() {
        return service.findAll();
    }

    @GetMapping(value = "/{id}")
    public Tour getTourById(@PathVariable("id") ObjectId id) {
        return service.findBy_id(id);
    }

    @PutMapping(value = "/update/{id}")
    public void updateTourById(@PathVariable("id") ObjectId id, @Valid @RequestBody Tour tour) {
        tour.set_id(id);
        service.save(tour);
    }

    @PostMapping(value = "/create")
    public Tour createTour(@Valid @RequestBody Tour tour) {
        tour.set_id(ObjectId.get());
        service.save(tour);
        return tour;
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteTour(@PathVariable ObjectId id) {
        service.delete(service.findBy_id(id));
    }

}
