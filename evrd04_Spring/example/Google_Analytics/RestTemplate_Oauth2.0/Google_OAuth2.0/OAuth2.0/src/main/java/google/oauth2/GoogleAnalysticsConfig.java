package google.oauth2;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;


@Configuration
public class GoogleAnalysticsConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){ // RestTemplateBuilder : Spring5부터 가능
        return builder
                .setConnectTimeout(Duration.ofSeconds(5))
                .setReadTimeout(Duration.ofSeconds(5))
                .build();
    }

    // 스프링3용 for product
//    @Bean
//    public RestTemplate restTemplate(){ // RestTemplateBuilder : Spring5부터 가능
//        return new RestTemplate(clientHttpRequestFactory());
//    }
//
//    private ClientHttpRequestFactory clientHttpRequestFactory() {
//        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
//        factory.setConnectTimeout(5);
//        factory.setReadTimeout(5);
//        return factory;
//    }

}
