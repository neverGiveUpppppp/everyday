package kr.co.polycube.backendtest.common;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<FilterUrl> specialCharacterFilter() {
        FilterRegistrationBean<FilterUrl> filter = new FilterRegistrationBean<>();
        filter.setFilter(new FilterUrl());
        filter.addUrlPatterns("/*");    // filter가 적용된 url 패턴
        filter.setOrder(1);             // filter 간 작동 순서 지정(필터가 하나라면 생략가능)
        return filter;
    }

}
