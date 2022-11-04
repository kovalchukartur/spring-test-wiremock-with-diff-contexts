package com.example.wiremockport;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ContextConfiguration(classes = {
    RestClient.class
},
    initializers = {
        ConfigDataApplicationContextInitializer.class,
    })
@ExtendWith(SpringExtension.class)
@TestPropertySource("/application.properties")
@EnableConfigurationProperties(value = {
    UrlProperties.class
})
@AutoConfigureWireMock(port = 0, stubs = "classpath:/stubs")
class RestClientUnitTest {

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