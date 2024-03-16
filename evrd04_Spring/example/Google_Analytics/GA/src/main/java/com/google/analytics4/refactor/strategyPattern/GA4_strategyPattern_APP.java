package com.google.analytics4.refactor.strategyPattern;

import com.google.analytics4.refactor.adapterPattern.GoogleAnalytics4VO;

import java.io.IOException;

public class GA4_strategyPattern_APP {
    public static void main(String[] args) throws IOException {

        AnalyticsDataClient analyticsData = new GoogleAnalyticsDataClient();
        AnalyticsService service = new AnalyticsService(analyticsData);
        GoogleAnalytics4VO ga4Vo = service.ga4VisitorNum();

        System.out.println(ga4Vo.getTodayVisitors());
        System.out.println(ga4Vo.getAllVisitors());

    }
}
