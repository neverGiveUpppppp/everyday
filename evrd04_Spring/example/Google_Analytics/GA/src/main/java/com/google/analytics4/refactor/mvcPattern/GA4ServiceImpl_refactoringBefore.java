package com.google.analytics4.refactor.mvcPattern;

import com.google.analytics.data.v1beta.*;

public class GA4ServiceImpl_refactoringBefore  {
    private final AnalyticsDataClient analyticsData;

    public GA4ServiceImpl_refactoringBefore(AnalyticsDataClient analyticsData) {
        this.analyticsData = analyticsData;
    }
//    private final AnalyticsDataClient analyticsData;
//    private final CacheManager cacheManager;
//
//    @Autowired
//    public GA4ServiceImpl(AnalyticsDataClient analyticsData, CacheManager cacheManager) {
//        this.analyticsData = analyticsData;
//        this.cacheManager = cacheManager;
//    }


    public GoogleAnalytics4VO ga4VisitorNum() {
//        String propertyId = "425548737";  // 테스트용 내 계정
        String propertyId = "425228597";  // 운영 서버
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
                .addMetrics(Metric.newBuilder().setName("activeUsers")) // 옵션 : activeUsers, totalUsers, newUsers (참조 :https://developers.google.com/analytics/devguides/reporting/data/v1/api-schema#metrics)
                .addDateRanges(DateRange.newBuilder().setStartDate(startDate).setEndDate(endDate))
                .build();
        RunReportResponse response = analyticsData.runReport(request);
        return response.getRows(0).getMetricValues(0).getValue();
    }




}
