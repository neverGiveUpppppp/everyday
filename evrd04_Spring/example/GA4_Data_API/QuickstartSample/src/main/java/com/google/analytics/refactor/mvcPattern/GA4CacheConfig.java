package com.google.analytics.refactor.mvcPattern;

import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
@EnableCaching
public class GA4CacheConfig {

//    @Bean
//    public CacheManager cacheManager() {
//        return new ConcurrentMapCacheManager("visitorCounts"); // 캐시 매니저 등록, "visitorCounts" 캐시 생성
//    }
//
//    @Bean
//    public EhCacheCacheManager cacheManager(CacheManager cm) {
//        return new EhCacheCacheManager(ehCacheManager);
//    }
    
    @Bean
    public CacheManager ehCacheManager() {
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder() // CacheManagerBuilder를 사용하여 캐시 매니저 구성 시작
                .withCache("visitorCounts", // "visitorCounts"라는 이름의 캐시 생성
                        CacheConfigurationBuilder.newCacheConfigurationBuilder(
                                Long.class, Integer.class, // 캐시 키와 값의 타입 지정
                                ResourcePoolsBuilder.heap(100)) // 힙 메모리 내에 최대 100개의 엔트리를 저장
//                                .withExpiry(org.ehcache.expiry.Expirations.timeToLiveExpiration(java.time.Duration.ofMinutes(5))) // TTL 설정: 5분 동안 유효
                                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(java.time.Duration.ofMinutes(120))) // TTL 설정: 항목은 5분 동안 유효합니다.
                ).build(); // 캐시 매니저 빌드
        cacheManager.init(); // 캐시 매니저 초기화
        return cacheManager;
    }
    
}
