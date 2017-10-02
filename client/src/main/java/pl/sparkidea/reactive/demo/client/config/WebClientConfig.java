package pl.sparkidea.reactive.demo.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Maciej Lesniak
 */
@Configuration
public class WebClientConfig {

    @Bean
    public WebClient eventServerClient() {
        return WebClient.create("http://localhost:8080");
    }

}
