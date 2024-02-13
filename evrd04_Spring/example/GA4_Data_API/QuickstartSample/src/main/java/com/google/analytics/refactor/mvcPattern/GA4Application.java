package com.google.analytics.refactor.mvcPattern;

import com.google.analytics.AnalyticsApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
        SpringApplication.run(com.google.analytics.AnalyticsApplication.class, args);

        try {
            AnalyticsDataClient analyticsDataClient = new GoogleAnalyticsDataClient();
            GA4ServiceImpl analyticsService = new GA4ServiceImpl(analyticsDataClient);
//            GA4Controller controller = new GA4Controller((GA4ServiceImpl) analyticsService);
//            GoogleAnalytics4VO ga4Vo = controller.getAnalyticsData();

            GoogleAnalytics4VO ga4Vo = analyticsService.ga4VisitorNum();
            System.out.println(ga4Vo.getTodayVisitors());
            System.out.println(ga4Vo.getAllVisitors());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
