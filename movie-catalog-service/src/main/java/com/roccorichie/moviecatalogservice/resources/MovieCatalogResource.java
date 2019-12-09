package com.roccorichie.moviecatalogservice.resources;


import com.roccorichie.moviecatalogservice.models.CatalogItem;
import com.roccorichie.moviecatalogservice.models.Movie;
import com.roccorichie.moviecatalogservice.models.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

        List<Rating> ratings = Arrays.asList(
                new Rating("1234", 4),
                new Rating("5678", 3)
        );

        return ratings.stream().map(rating -> {
            // RestTemplate -> Making a call to the API and un-marshalling the object
            // Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);

            // This builder will give an instance of movie
            // Using builder pattern give me a webclient
            // get method
            // uri -> url
            // Go do the fetch
            // Whatever body you get back convert it to an instance of
            // mono -> a promise that this will give you back this (movie) object asynchronously
            // An empty container - when something is in it return it to the caller
            // block - block execution until someone puts a movie into the container - synchronous
            Movie movie = webClientBuilder.build()
                    .get()
                    .uri("http://localhost:8082/movies/" + rating.getMovieId())
                    .retrieve()
                    .bodyToMono(Movie.class)
                    .block();

            assert movie != null;
            return new CatalogItem(movie.getName(), "Awesome", rating.getRating());
        }).collect(Collectors.toList());


    }
}
