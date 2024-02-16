//package com.google.analytics.refactor.mvcPattern;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class GA4InterceptorConfig implements WebMvcConfigurer {
//
//    private final GA4Interceptor interceptor;
//
//    public GA4InterceptorConfig(GA4Interceptor interceptor){
//        this.interceptor = interceptor;
//    }
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(interceptor).addPathPatterns("/**"); // 인터셉터 등록 및 동작URL 설정
//    }
//
//}
