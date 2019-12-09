package com.roccorichie.moviecatalogservice.resources;


import com.roccorichie.moviecatalogservice.models.CatalogItem;
import com.roccorichie.moviecatalogservice.models.Movie;
import com.roccorichie.moviecatalogservice.models.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

        List<Rating> ratings = Arrays.asList(
                new Rating("1234", 4),
                new Rating("5678", 3)
        );

        return ratings.stream().map(rating -> {
            // RestTemplate -> Making a call to the API and un-marshalling the object
            Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);

            assert movie != null;
            return new CatalogItem(movie.getName(), "Awesome", rating.getRating());
        }).collect(Collectors.toList());


    }
}
