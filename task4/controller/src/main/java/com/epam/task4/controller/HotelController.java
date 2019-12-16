package com.epam.task4.controller;

import com.epam.task4.entity.Hotel;
import com.epam.task4.service.HotelService;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/agency/hotels", produces = MediaType.APPLICATION_JSON_VALUE)
public class HotelController {

    private final HotelService service;

    public HotelController(HotelService service) {
        this.service = service;
    }

    @GetMapping(value = "/")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    public List<Hotel> getAllHotels() {
        return service.findAll();
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    public Hotel getHotelById(@PathVariable("id") ObjectId id) {
        return service.findBy_id(id);
    }

    @PutMapping(value = "/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    public void updateHotelById(@PathVariable("id") ObjectId id, @Valid @RequestBody Hotel hotel) {
        hotel.set_id(id);
        service.save(hotel);
    }

    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Hotel createHotel(@Valid @RequestBody Hotel hotel) {
        hotel.set_id(ObjectId.get());
        service.save(hotel);
        return hotel;
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void deleteHotel(@PathVariable ObjectId id) {
        service.delete(service.findBy_id(id));
    }
}
