package nl.hu.bep3.kees.stock.infrastructure.config;

import nl.hu.bep3.kees.stock.infrastructure.driven.storage.HttpMealRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class HttpClientConfig {
    @Value("${http-client.root-path.meals}")
    private String rootPath;

    @Bean
    public HttpMealRepository httpMealRepository() { return new HttpMealRepository(rootPath, restTemplate()); }

    @Bean
    public RestTemplate restTemplate() { return new RestTemplate(); }
}
