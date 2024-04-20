package com.google.analytics.GA4OnAir.impl;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.analytics.data.v1beta.DateRange;
import com.google.analytics.data.v1beta.Dimension;
import com.google.analytics.data.v1beta.Metric;
import com.google.analytics.data.v1beta.RunReportRequest;
import com.google.analytics.data.v1beta.RunReportResponse;

import zesinc.user.ga4.AnalyticsData;
import zesinc.user.ga4.GoogleAnalyticsDataService;

@Service
public class GoogleAnalyticsDataServiceImpl implements GoogleAnalyticsDataService{
    
  private final AnalyticsData analyticsData;
  private final CacheManager cacheManager;
  
  @Autowired
  public GoogleAnalyticsDataServiceImpl(AnalyticsData analyticsData, CacheManager cacheManager) {
      this.analyticsData = analyticsData;
      this.cacheManager = cacheManager;
  }
  
    // 오늘의 방문자 수 
    public String ga4CacheToday() {
//      String propertyId = "425548737";
      String propertyId = "425228597";   // 운영서버 
        Cache<String, String> cache = cacheManager.getCache("visitorCounts", String.class, String.class);
    
        // 오늘 방문자 수 조회
        String todayNum = "todayNum";          // 캐쉬데이터 키값 초기화
        String todayCnt = cache.get(todayNum); // 캐쉬값 로드
        if (todayCnt == null) {
            String today = getVisitors(propertyId, "today", "today", true); // GA4에서 방문자 수 가져오기
            // 캐시에 저장
            cache.put(todayNum, today);
            todayCnt = cache.get(todayNum);
        }
        return todayCnt;
    } 
    
    // 누적 총 사용자 수
    public String ga4CacheAll() {
//        String propertyId = "425548737";
        String propertyId = "425228597";   // 운영서버 
        Cache<String, String> cache = cacheManager.getCache("visitorCounts", String.class, String.class);
    
        // 전체 방문자 수 조회
        String allNum = "allNum";          // 캐쉬데이터 키값 초기화
        String allCnt = cache.get(allNum); // 캐쉬값 로드
    
        if (allCnt == null) {
          String all = getVisitors(propertyId, "2024-01-28", "today", false); 
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
                // 실패 시 재시도 로직 
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

    // GA4 API 호출
    private String tryRunReport(String propertyId, String startDate, String endDate) throws Exception {
        RunReportRequest request = RunReportRequest.newBuilder()
                .setProperty("properties/" + propertyId)
                .addDimensions(Dimension.newBuilder().setName("city"))
                .addMetrics(Metric.newBuilder().setName("totalUsers"))
                .addDateRanges(DateRange.newBuilder().setStartDate(startDate).setEndDate(endDate))
                .build();
        RunReportResponse response = analyticsData.runReport(request);
        return response.getRows(0).getMetricValues(0).getValue();
    }
    
}
