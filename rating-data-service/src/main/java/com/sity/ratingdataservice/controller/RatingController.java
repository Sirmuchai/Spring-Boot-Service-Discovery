package com.sity.ratingdataservice.controller;

import com.sity.ratingdataservice.model.Rating;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/rating")
public class RatingController {

    @GetMapping("/{movieId}")
    public Rating getRatings(@PathVariable("movieId") String movieId){
        return new Rating(movieId, 4);
    }
}
