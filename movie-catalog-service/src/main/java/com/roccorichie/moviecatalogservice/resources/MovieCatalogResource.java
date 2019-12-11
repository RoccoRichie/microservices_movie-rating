package com.roccorichie.moviecatalogservice.resources;


import com.roccorichie.moviecatalogservice.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/{userId}")
    public MovieCatalog getCatalog(@PathVariable("userId") String userId) {
    //public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

        UserRating ratings = restTemplate.getForObject("http://localhost:8083/ratingsdata/users/" + userId, UserRating.class);

        assert ratings != null;
        List<CatalogItem> movieList = ratings.getUserRating().stream().map(rating -> {
            // RestTemplate -> Making a call to the API and un-marshalling the object
            // This is synchronous - not asynchronous (WebClient)
            Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);

            assert movie != null;
            return new CatalogItem(movie.getName(), "Awesome", rating.getRating());
        }).collect(Collectors.toList());
        MovieCatalog movieCatalog = new MovieCatalog();
        movieCatalog.setMovieCatalog(movieList);
        return movieCatalog;
    }
}
