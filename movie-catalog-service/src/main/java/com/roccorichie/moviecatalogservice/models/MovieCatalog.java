package com.roccorichie.moviecatalogservice.models;

import java.util.List;

public class MovieCatalog {
    List<CatalogItem> movieCatalog;

    public MovieCatalog() {
    }

    public List<CatalogItem> getMovieCatalog() {
        return movieCatalog;
    }

    public void setMovieCatalog(List<CatalogItem> movieCatalog) {
        this.movieCatalog = movieCatalog;
    }
}
