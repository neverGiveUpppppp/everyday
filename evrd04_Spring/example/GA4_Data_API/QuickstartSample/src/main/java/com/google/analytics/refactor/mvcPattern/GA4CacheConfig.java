package com.google.analytics.refactor.mvcPattern;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
//@EnableCaching // Spring Framework의 캐시 추상화 기능 활성화 : Spring의 캐시 관련 어노테이션(@Cacheable, @CacheEvict 등)을 사용
public class GA4CacheConfig {

//    private final CacheManager cacheManager;
//
//    public GA4CacheConfig(CacheManager cacheManager) {
//        this.cacheManager = cacheManager;
//    }
//    private CacheManager cacheManager;
//
//    public GA4CacheConfig() {
//    }

//    @Bean(initMethod = "init", destroyMethod = "") // java.lang.IllegalStateException: Init not supported from AVAILABLE 발생 : 외부 라이브러리 지원되는 초기화 생성 방법인데 스프링과 Ehcache의 버젼 문제인지 안된다고 함
    @Bean
    public CacheManager ehCacheManager() {
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder() // CacheManagerBuilder를 사용하여 캐시 매니저 구성 시작
                .withCache("visitorCounts", // "visitorCounts"라는 이름의 캐시 생성 // 캐시 컨테이너를 의미
                        CacheConfigurationBuilder.newCacheConfigurationBuilder(
                                String.class, String.class, // 캐시 키와 값의 타입 지정
                                ResourcePoolsBuilder.heap(100)) // 힙 메모리 내에 최대 100개의 엔트리를 저장
//                                .withExpiry(org.ehcache.expiry.Expirations.timeToLiveExpiration(java.time.Duration.ofMinutes(5))) // (deprecated)TTL 설정: 5분 동안 유효
                                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(java.time.Duration.ofMinutes(1))) // TTL 설정: 항목은 120분 동안 유효합니다.
//                ).build(); // 캐시 매니저 빌드 //
        ).build(true); // build 메서드에 true를 전달하여 캐시 매니저를 바로 초기화함
//        cacheManager.init(); // 캐시 매니저 초기화
        return cacheManager;
    }

}
