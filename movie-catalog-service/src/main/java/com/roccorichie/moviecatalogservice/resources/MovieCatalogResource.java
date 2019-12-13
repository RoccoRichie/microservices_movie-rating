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
        // remove the hardcoded URL and use the load balanced to query eureka
        UserRating ratings = restTemplate.getForObject("http://movie-rating-service/ratingsdata/users/" + userId, UserRating.class);

        assert ratings != null;
        List<CatalogItem> movieList = ratings.getUserRating().stream().map(rating -> {
            Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);

            assert movie != null;
            return new CatalogItem(movie.getName(), "Awesome", rating.getRating());
        }).collect(Collectors.toList());
        MovieCatalog movieCatalog = new MovieCatalog();
        movieCatalog.setMovieCatalog(movieList);
        return movieCatalog;
    }
}
