package google.oauth2.product.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;



@Configuration
public class GoogleAnalysticsConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate(clientHttpRequestFactory());
    }

    @Bean
        public ClientHttpRequestFactory clientHttpRequestFactory() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(10*1000); // 10초 설정 : milliseconds단위
        factory.setReadTimeout(10*1000);
        return factory;
    }

    @Bean
    public Gson gson() {
        return new Gson();
    }
    
    
}
