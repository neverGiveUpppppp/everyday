package com.google.analytics.refactor.mvcPattern;

import com.google.analytics.data.v1beta.*;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GA4ServiceImpl implements GA4Service{
    private final AnalyticsDataClient analyticsData;
//    private final CacheManager cacheManager;

//    public GA4ServiceImpl(AnalyticsDataClient analyticsData, CacheManager cacheManager) {
//        this.analyticsData = analyticsData;
//        this.cacheManager = cacheManager;
//    }
    public GA4ServiceImpl(AnalyticsDataClient analyticsData) {
        this.analyticsData = analyticsData;
    }

    public GoogleAnalytics4VO ga4VisitorNum() {
        String propertyId = "425548737";
        GoogleAnalytics4VO ga4Vo = new GoogleAnalytics4VO();

        // 오늘 방문자 수 조회
        String todayVisitors = getVisitors(propertyId, "today", "today", true);
        ga4Vo.setTodayVisitors(todayVisitors);

        // 전체 방문자 수 조회
        String allVisitors = getVisitors(propertyId, "2024-01-28", "today", false);
        ga4Vo.setAllVisitors(allVisitors);

        return ga4Vo;
    }

    private String getVisitors(String propertyId, String startDate, String endDate, boolean retryOnFailure) {
        try {
            return tryRunReport(propertyId, startDate, endDate);
        } catch (Exception e) {
            if (retryOnFailure) {
                // 실패 시 재시도 로직 (예를 들어, yesterday부터 today까지의 데이터로 재시도)
                try {
                    return tryRunReport(propertyId, "yesterday", endDate);
                } catch (Exception retryException) {
                    retryException.printStackTrace();
                }
            } else {
                e.printStackTrace();
            }
        }
        return "0"; // 실패 시 기본값 반환
    }

    private String tryRunReport(String propertyId, String startDate, String endDate) throws Exception {
        RunReportRequest request = RunReportRequest.newBuilder()
                .setProperty("properties/" + propertyId)
                .addDimensions(Dimension.newBuilder().setName("city"))
                .addMetrics(Metric.newBuilder().setName("activeUsers"))
                .addDateRanges(DateRange.newBuilder().setStartDate(startDate).setEndDate(endDate))
                .build();
        RunReportResponse response = analyticsData.runReport(request);
        return response.getRows(0).getMetricValues(0).getValue();
    }


//    @Autowired
//    public Integer getCacheTodayCnt(CacheManager cacheManager) {
//        Cache<String, Integer> cache = cacheManager.getCache("visitorCounts", String.class, Integer.class);
//
//        System.out.println("getStatus() : " + cacheManager.getStatus());
//
//        // 캐쉬데이터 키값 초기화
//        String todayNum = "todayNum";
//        // 캐쉬값 로드
//        Integer todayCnt = cache.get(todayNum);
//
//        if (todayCnt == null) {
//            Integer today = Integer.valueOf(ga4VisitorNum().getTodayVisitors()); // GA4에서 방문자 수 가져오기
//            // 캐시에 저장
//            cache.put(todayNum, today);
//        }
//        // vo에 넣어야 하나? 값을?
//        return todayCnt;
//    }
//
//    @Autowired
//    public Integer getCacheAllCnt(CacheManager cacheManager) {
//        Cache<String, Integer> cache = cacheManager.getCache("visitorCounts", String.class, Integer.class);
//
//        System.out.println("getStatus() : " + cacheManager.getStatus());
//
//        // 캐쉬데이터 키값 초기화
//        String allNum = "allNum";
//        // 캐쉬값 로드
//        Integer allCnt = cache.get(allNum);
//
//        if (allCnt == null) {
//            Integer all = Integer.valueOf(ga4VisitorNum().getAllVisitors());
//            // 캐시에 저장
//            cache.put(allNum, all);
//        }
//        // vo에 넣어야 하나? 값을?
//        return allCnt;
//    }


}