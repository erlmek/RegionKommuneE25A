package org.example.regionkommunee25a.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Bean
    public RestClient.Builder restClientBuilder() {
        IO.println("Creating RestClient.Builder");
        return RestClient.builder();
    }

    @Bean
    public RestClient restClient(RestClient.Builder builder) {
        IO.println("Creating RestClient");
        return builder
                .baseUrl("https://api.example.com")
                .defaultHeader("Accept", "application/json")
                .build();
    }

}
