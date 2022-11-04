package com.example.wiremockport;

import com.github.tomakehurst.wiremock.WireMockServer;
import java.net.URI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.autoconfigure.properties.PropertyMapping;
import org.springframework.boot.test.autoconfigure.properties.SkipPropertyMapping;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.util.UriComponentsBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ContextConfiguration(
    classes = {
        RestClient.class
    },
    initializers = {
        ConfigDataApplicationContextInitializer.class
    }
)
@ExtendWith(SpringExtension.class)
@TestPropertySource("/application.properties")
@EnableConfigurationProperties(value = {
    UrlProperties.class
})
@AutoConfigureWireMock
@PropertyMapping(value = "wiremock.server", skip = SkipPropertyMapping.NO)
class RestClientUnitTest {

    @Autowired
    private WireMockServer wireMockServer;
    @Autowired
    private UrlProperties urlProperties;
    @Autowired
    private RestClient restClient;

    @BeforeEach
    public void setUp() {
        int port = wireMockServer.port();
        URI uri = UriComponentsBuilder.fromUri(urlProperties.getUri())
            .port(port)
            .build()
            .toUri();

        urlProperties.setUri(uri);
    }

    @Test
    void whenGetResourceIsSuccess() {
        String result = restClient.getResource(urlProperties.getUri());

        assertNotNull(result);
        assertEquals("test", result);
    }
}