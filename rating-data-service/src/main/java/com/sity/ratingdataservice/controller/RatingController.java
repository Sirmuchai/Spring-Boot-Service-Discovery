package com.sity.ratingdataservice.controller;

import com.sity.ratingdataservice.model.Rating;
import com.sity.ratingdataservice.model.UserRating;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/rating")
public class RatingController {

    @GetMapping("/{movieId}")
    public Rating getRatings(@PathVariable("movieId") String movieId){
        return new Rating(movieId, 4);
    }

    @GetMapping("/users/{userId}")
    public UserRating getUserRating(@PathVariable("userId") String userId){
        List<Rating> ratings = Arrays.asList(
                new Rating("550", 4),
                new Rating("100",3)
        );
        UserRating userRating = new UserRating();
        userRating.setUserRating(ratings);

        return userRating;
    }
}
