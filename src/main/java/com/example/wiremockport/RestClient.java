package com.example.wiremockport;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestClient {

    private final RestTemplate restTemplate;

    public RestClient() {
        this.restTemplate = new RestTemplate();
    }

    public String getResource() {
        return restTemplate.getForObject("https://spring.io/blog.atom", String.class);
    }
}
