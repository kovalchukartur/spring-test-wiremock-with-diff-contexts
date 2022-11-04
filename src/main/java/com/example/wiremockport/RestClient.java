package com.example.wiremockport;

import java.net.URI;
import java.net.URL;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestClient {

    private final RestTemplate restTemplate;

    public RestClient() {
        this.restTemplate = new RestTemplate();
    }

    public String getResource(URI uri) {
        return restTemplate.getForObject(uri, String.class);
    }
}
