package com.roccorichie.movieratingservice.resources;

import com.roccorichie.movieratingservice.models.Rating;
import com.roccorichie.movieratingservice.models.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingsdata")
public class RatingResource {

    @RequestMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId) {
        // This will always return a rating of 4 for any movieId
        return new Rating(movieId, 4);
    }

    // Create another API endpoint -
    @RequestMapping("users/{userId}")
    public UserRating getUserRating(@PathVariable("userId") String userId) {
    // public List<Rating> getUserRating(@PathVariable("userId") String userId) {
        List<Rating> ratings = Arrays.asList(
                new Rating("1234", 4),
                new Rating("5678", 3)
        );
        // this API returns a list as the root node - Not recommended
        // An Api should return an object because if you add a new field you will
        // not be breaking a contract - the consumer can ignore the new field
        // return ratings;
        // API root should always be an object - even in this case we are
        // wrapping the list into a UserRating object
        UserRating userRating = new UserRating();
        userRating.setUserRating(ratings);
        return userRating;
    }
}
