package com.example.wiremockport;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureWireMock
class RestClientTest {

    @Autowired
    private UrlProperties urlProperties;

    @Autowired
    private RestClient restClient;

    @Test
    void whenGetResourceIsSuccess() {
        String result = restClient.getResource(urlProperties.getUrl());

        assertNotNull(result);
        assertEquals("test", result);
    }
}