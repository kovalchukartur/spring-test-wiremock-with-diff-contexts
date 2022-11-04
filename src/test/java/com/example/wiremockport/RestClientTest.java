package com.example.wiremockport;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RestClientTest {

    @Autowired
    private UrlProperties urlProperties;

    @Autowired
    private RestClient restClient;

    @Test
    void whenGetResourceIsSuccess() {
        String resource = restClient.getResource(urlProperties.getUrl());

        assertNotNull(resource);
    }
}