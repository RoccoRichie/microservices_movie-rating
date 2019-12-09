package com.roccorichie.moviecatalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MovieCatalogServiceApplication {

    // Create a Bean which will create a singleton version of the RestTemplate
    // And make the instance be available for when it is required i.e
    // take it out of getCatalog(), it is a singleton
    // RestTemplates maps to this one instance - Content Delivery Injection
    // A bean is telling Spring I have something which others may need (a producer)
    // - Autowired says give me that something
    // The name doesn't matter - the type does
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(MovieCatalogServiceApplication.class, args);
    }

}
