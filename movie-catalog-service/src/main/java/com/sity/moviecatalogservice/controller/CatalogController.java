package com.sity.moviecatalogservice.controller;

import com.sity.moviecatalogservice.model.CatalogItem;
import com.sity.moviecatalogservice.model.Movie;
import com.sity.moviecatalogservice.model.UserRating;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@NoArgsConstructor
@RequestMapping("api/v1/catalog")
public class CatalogController {


    private RestTemplate restTemplate;

    @Autowired
    public CatalogController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

        UserRating userRating = restTemplate.getForObject("http://rating-data-service/api/v1/rating/users/" + userId, UserRating.class);

        return userRating.getUserRating().stream()
                .map(rating -> {
                    Movie movie = restTemplate.getForObject("http://movie-info-service/api/v1/movie/" + rating.getMovieId(), Movie.class);
                    return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
                })
                .collect(Collectors.toList());

    }



}
