package google.oauth2.product.config;


import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ExpiryPolicyBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class GACacheConfig {

    @Bean
    public CacheManager ehCacheManager() { 
        CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder()
            .withCache("visitorCounts",     // "visitorCounts" 키명으로 캐시 생성
                CacheConfigurationBuilder.newCacheConfigurationBuilder(
                        String.class, String.class,     // 캐시 키와 밸류값의 타입 설정
                        ResourcePoolsBuilder.heap(10))  // 힙에 최대 10개의 항목을 저장
                        .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(java.time.Duration.ofMinutes(2))) // TTL(Time to Live) 설정 옵션 
            ).build();
        cacheManager.init(); // 초기화 콜백
        return cacheManager;
    }
}
