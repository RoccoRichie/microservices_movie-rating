package com.roccorichie.moviecatalogservice.resources;


import com.roccorichie.moviecatalogservice.models.CatalogItem;
import com.roccorichie.moviecatalogservice.models.Movie;
import com.roccorichie.moviecatalogservice.models.Rating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

// Make this a Rest Controller/Resource:
@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
    //Tell spring boot to treat this as an api -> give it an end point
    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

        // make a call and get string response - Un-marshall it - create an object instance of it
        RestTemplate restTemplate = new RestTemplate();
        // Get all rated movie Ids - Will hard code this first
        // Hardcoded for now - Assume this is the response (for a user) from hitting the Movie rating api
        List<Rating> ratings = Arrays.asList(
                new Rating("1234", 4),
                new Rating("5678", 3)
        );

        // Use restTemplate (will be replaced by webclient in future)
        // Make API call using rest template: Could use for loop instead of map
        // For each movie Id call, call(rest) movie info service and get details
        // Put them all together
        // Should replace url with service discovery
        return ratings.stream().map(rating -> {
            Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);
            assert movie != null;
            return new CatalogItem(movie.getName(), "Awesome", rating.getRating());
        }).collect(Collectors.toList());


    }
}
