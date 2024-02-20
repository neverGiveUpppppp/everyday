package com.google.analytics.refactor.mvcPattern;

import com.google.analytics.data.v1beta.*;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class GA4ServiceImpl {  // refactoring한 클래스
//    private final AnalyticsDataClient analyticsData;
//
//    public GA4ServiceImpl(AnalyticsDataClient analyticsData) {
//        this.analyticsData = analyticsData;
//    }
    private final AnalyticsDataClient analyticsData;
    private final CacheManager cacheManager;

    @Autowired
    public GA4ServiceImpl(AnalyticsDataClient analyticsData, CacheManager cacheManager) {
        this.analyticsData = analyticsData;
        this.cacheManager = cacheManager;
    }

    //        String propertyId = "425548737";
    String propertyId = "425228597"; // 운영

    public String ga4CacheToday() {
        Cache<String, String> cache = cacheManager.getCache("visitorCounts", String.class, String.class);

        // 오늘 방문자 수 조회
        String todayNum = "todayNum"; // 캐쉬데이터 키값 초기화
        String todayCnt = cache.get(todayNum); // 캐쉬값 로드
        if (todayCnt == null) {
            String today = getVisitors(propertyId, "today", "today", true); // GA4에서 방문자 수 가져오기
            // 캐시에 저장
            cache.put(todayNum, today);
            todayCnt = cache.get(todayNum);
        }
        return todayCnt;
    }

    public String ga4CacheAll() {
        Cache<String, String> cache = cacheManager.getCache("visitorCounts", String.class, String.class);

        // 전체 방문자 수 조회
        String allNum = "allNum";  // 캐쉬데이터 키값 초기화
        String allCnt = cache.get(allNum); // 캐쉬값 로드

        if (allCnt == null) {
            String all = getVisitors(propertyId, "2024-01-01", "today", false); // ga4VisitorNum()에서 이미 두 수를 다 가져옴
            // 캐시에 저장
            cache.put(allNum, all);
            allCnt = cache.get(allNum);
        }
        return allCnt;
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
                .addDimensions(Dimension.newBuilder().setName("country")) // city, country // city로 800였으나 country로 바구니 2100 제대로 나옴
                .addMetrics(Metric.newBuilder().setName("activeUsers")) // activeUsers, totalUsers, newUsers
                .addDateRanges(DateRange.newBuilder().setStartDate(startDate).setEndDate(endDate))
                .build();
        RunReportResponse response = analyticsData.runReport(request);
        return response.getRows(0).getMetricValues(0).getValue();
    }




}
