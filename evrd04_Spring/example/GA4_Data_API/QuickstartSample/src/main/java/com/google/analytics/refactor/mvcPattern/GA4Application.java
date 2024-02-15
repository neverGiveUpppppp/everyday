package com.google.analytics.refactor.mvcPattern;

import com.google.analytics.AnalyticsApplication;
import com.google.common.cache.Cache;
import org.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class GA4Application {
//    public static void main(String[] args) { // java전용 spring X
//        try {
//            AnalyticsDataClient analyticsDataClient = new GoogleAnalyticsDataClient();
//            GA4ServiceImpl analyticsService = new GA4ServiceImpl(analyticsDataClient);
//            GA4Controller controller = new GA4Controller((GA4ServiceImpl) analyticsService);
//
//            GoogleAnalytics4VO data = controller.getAnalyticsData();
//            System.out.println(data.getTodayVisitors());
//            System.out.println(data.getAllVisitors());
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }


    public static void main(String[] args) {
        SpringApplication.run(AnalyticsApplication.class, args);

        try{
        AnalyticsDataClient analyticsData = new GoogleAnalyticsDataClient();
        GA4ServiceImpl service = new GA4ServiceImpl(analyticsData);
        GA4Controller controller = new GA4Controller(service);

        GoogleAnalytics4VO ga4Vo = controller.getAnalyticsData();


        }catch(Exception e){
            e.printStackTrace();
        }

        try {
//            Cache<String, Integer> cache = new Cache<>();
//            AnalyticsDataClient analyticsDataClient = new GoogleAnalyticsDataClient();
//            GA4ServiceImpl analyticsService = new GA4ServiceImpl(analyticsDataClient);
//            GA4Controller controller = new GA4Controller((GA4ServiceImpl) analyticsService);
//            GoogleAnalytics4VO ga4Vo = controller.getAnalyticsData();

//            AnalyticsDataClient analyticsDataClient = new GoogleAnalyticsDataClient();
//            GA4Service service = new GA4ServiceImpl(analyticsDataClient);
//            GA4ServiceCache cache = new GA4ServiceCache(service);
//            System.out.println("cache : " + cache.getCacheTodayCnt());
//            System.out.println("cache : " + cache.getCacheAllCnt());

//            ApplicationContext ac = new ApplicationContext(GA4CacheConfig.class);

            AnalyticsDataClient analyticsDataClient = new GoogleAnalyticsDataClient();
            GA4Service service = new GA4ServiceImpl(analyticsDataClient);
            AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(GA4CacheConfig.class);

//            GA4CacheConfig cache = ac.getBean("ehCacheManager", GA4CacheConfig.class);
//            GA4ServiceCache ga4Cache = new GA4ServiceCache(service, (CacheManager) cache);

            CacheManager cm = ac.getBean("ehCacheManager", CacheManager.class);  // 스프링 컨테이너에서 직접 가져오는 방식으로 캐쉬데이터 끌어옴
            GA4Cache ga4Cache = new GA4Cache(service, cm);
            ga4Cache.getCacheTodayCnt();
            ga4Cache.getCacheAllCnt();

            System.out.println(ga4Cache.getCacheTodayCnt());
            System.out.println(ga4Cache.getCacheAllCnt());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
