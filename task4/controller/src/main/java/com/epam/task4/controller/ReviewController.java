package com.epam.task4.controller;


import com.epam.task4.entity.Review;
import com.epam.task4.service.ReviewService;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/agency/reviews")
public class ReviewController {

    private final ReviewService service;

    public ReviewController(ReviewService service) {
        this.service = service;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Review> getAllReviews() {
        return service.findAll();
    }

    @GetMapping(value = "/{id}")
    public Review getReviewById(@PathVariable("id") ObjectId id) {
        return service.findBy_id(id);
    }

    @PutMapping(value = "/update/{id}")
    public void updateReviewById(@PathVariable("id") ObjectId id, @Valid @RequestBody Review review) {
        review.set_id(id);
        service.save(review);
    }

    @PostMapping(value = "/create")
    public Review createReview(@Valid @RequestBody Review review) {
        review.set_id(ObjectId.get());
        service.save(review);
        return review;
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteReview(@PathVariable ObjectId id) {
        service.delete(service.findBy_id(id));
    }
}

