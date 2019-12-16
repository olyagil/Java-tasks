package com.epam.task4.controller;

import com.epam.task4.entity.Country;
import com.epam.task4.service.CountryService;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/agency/countries")
public class CountryController {

    private final CountryService service;

    public CountryController(CountryService service) {
        this.service = service;
    }

    @GetMapping(value = "/")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_ADMIN')")
    public List<Country> getAllCountries() {
        return service.findAll();
    }

    @GetMapping(value = "/by-id/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_ADMIN')")
    public Country getCountryById(@PathVariable("id") ObjectId id) {
        return service.findBy_id(id);
    }
    @GetMapping(value = "/by-name/{name}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_ADMIN')")
    public Country getCountryByName(@PathVariable("name") String name) {
        return service.findByName(name);
    }

    @PutMapping(value = "/update/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void updateCountryById(@PathVariable("id") ObjectId id, @Valid @RequestBody Country country) {
        country.set_id(id);
        service.save(country);
    }

    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Country createCountry(@Valid @RequestBody Country country) {
        country.set_id(ObjectId.get());
        service.save(country);
        return country;
    }

    @DeleteMapping(value = "/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public void deleteCountry(@PathVariable ObjectId id) {
        service.delete(service.findBy_id(id));
    }
}
