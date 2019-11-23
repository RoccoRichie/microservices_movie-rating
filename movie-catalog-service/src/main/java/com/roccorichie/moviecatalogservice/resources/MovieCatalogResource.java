package com.roccorichie.moviecatalogservice.resources;


import com.roccorichie.moviecatalogservice.models.CatalogItem;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

// Make this a Rest Controller/Resource:
@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
    //Tell spring boot to treat this as an api -> give it an end point
    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

        return Collections.singletonList(
                new CatalogItem("Star Wars", "Awesome", 5)
        );
    }
}
