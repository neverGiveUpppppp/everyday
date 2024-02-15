package com.google.analytics.refactor.mvcPattern;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.springframework.stereotype.Component;

@Component
public class GA4Cache {

    private final GA4Service service;
    private final CacheManager cacheManager; // GA4CacheConfig의 @Bean등록한 ehCacheManager()의 타입 CacheManager

    public GA4Cache(GA4Service service, CacheManager cacheManager) {
        this.service = service;
        this.cacheManager = cacheManager;
    }


//    @Autowired
//    public void setCacheManager(GA4CacheConfig cacheManager) {
////        this.cacheManager = cacheManager.createCache("visitorCounts",GA4CacheConfig<String.class, Integer.class>);
//        this.cacheManager = cacheManager.ehCacheManager();
//    }

//    @Autowired
//    public GA4ServiceCache(GA4Service service) {
//        this.service = service;
//    }
//
//    @Autowired
//    public void setCacheManager(CacheManager cacheManager) {
//        this.cacheManager = cacheManager;
//    }


//    @Autowired
//    public Integer getCacheTodayCnt(CacheManager cacheManager) {
    public Integer getCacheTodayCnt() {
        Cache<String, Integer> cache = cacheManager.getCache("visitorCounts", String.class, Integer.class);

        // 캐쉬데이터 키값 초기화
        String todayNum = "todayNum";
        // 캐쉬값 로드
        Integer todayCnt = cache.get(todayNum);

        if (todayCnt == null) {
            Integer today = Integer.valueOf(service.ga4VisitorNum().getTodayVisitors()); // GA4에서 방문자 수 가져오기
            // 캐시에 저장
            cache.put(todayNum, today);
            todayCnt = cache.get(todayNum);
        }
        System.out.println("cache.toString() : " + cache.toString());
        System.out.println("todayCnt : " + todayCnt);
        // vo에 넣어야 하나? 값을?
        return todayCnt;
    }

//    @Autowired
//    public Integer getCacheAllCnt(CacheManager cacheManager) {
    public Integer getCacheAllCnt() {
        Cache<String, Integer> cache = cacheManager.getCache("visitorCounts", String.class, Integer.class);

        // 캐쉬데이터 키값 초기화
        String allNum = "allNum";
        // 캐쉬값 로드
        Integer allCnt = cache.get(allNum);

        if (allCnt == null) {
            Integer all = Integer.valueOf(service.ga4VisitorNum().getAllVisitors());
            // 캐시에 저장
            cache.put(allNum, all);
            allCnt = cache.get(allNum);
        }
        // vo에 넣어야 하나? 값을?
        return allCnt;
    }


}
