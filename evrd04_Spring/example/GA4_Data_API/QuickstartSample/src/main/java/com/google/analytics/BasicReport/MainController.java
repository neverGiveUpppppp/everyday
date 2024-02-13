//package com.google.analytics.BasicReport;
//
//import com.google.analytics.data.v1beta.*;
//
//public class MainController {
//
//    public static void main(String[] args) {
//
//    }
//
//    @Override
//    public GoogleAnalytics4VO ga4VisitorNum() {
//
//        String propertyId = "425548737";
//        GoogleAnalytics4VO ga4Vo = new GoogleAnalytics4VO();
//
//        try {
//            BetaAnalyticsDataClient analyticsData = BetaAnalyticsDataClient.create();
//            RunReportRequest request1 =
//                    RunReportRequest.newBuilder()
//                            .setProperty("properties/" + propertyId)
//                            // Dimensions
////                        .addDimensions(Dimension.newBuilder().setName("country"))
////                        .addDimensions(Dimension.newBuilder().setName("region"))
//                            .addDimensions(Dimension.newBuilder().setName("city"))
////                        .addDimensions(Dimension.newBuilder().setName("date"))
////                        .addDimensions(Dimension.newBuilder().setName("dateHour"))
////                        .addDimensions(Dimension.newBuilder().setName("dateHourMinute"))
//                            // Metrics
////                        .addMetrics(Metric.newBuilder().setName("activeUsers"))
////                        .addMetrics(Metric.newBuilder().setName("newUsers"))
//                            .addMetrics(Metric.newBuilder().setName("totalUsers"))
//                            .addDateRanges(DateRange.newBuilder().setStartDate("today").setEndDate("today"))
//                            .build();
//
//            RunReportResponse todayVisit = analyticsData.runReport(request1);
//
////        int todayVisitors = Integer.parseInt(todayVisit.getRows(0).getMetricValues(0).getValue());
////        int allVisitors = Integer.parseInt(allVisit.getRows(0).getMetricValues(0).getValue());
//
//            String todayVisitors = todayVisit.getRows(-1).getMetricValues(0).getValue();
//            ga4Vo.setTodayVisitors(todayVisitors);
//
//        }catch(Exception e) {
//            e.printStackTrace();
//            try {
//                BetaAnalyticsDataClient analyticsData = BetaAnalyticsDataClient.create();
//                RunReportRequest request1 =
//                        RunReportRequest.newBuilder()
//                                .setProperty("properties/" + propertyId)
//                                .addDimensions(Dimension.newBuilder().setName("city"))
//                                .addMetrics(Metric.newBuilder().setName("totalUsers"))
//                                .addDateRanges(DateRange.newBuilder().setStartDate("yesterday").setEndDate("today"))
//                                .build();
//
//                RunReportResponse todayVisit = analyticsData.runReport(request1);
//                String todayVisitors = todayVisit.getRows(0).getMetricValues(0).getValue();
//                ga4Vo.setTodayVisitors(todayVisitors);
//
//            }catch(Exception exception) {
//                exception.printStackTrace();
//            }
//        }
//        try{
//            BetaAnalyticsDataClient analyticsData = BetaAnalyticsDataClient.create();
//            RunReportRequest request2 =
//                    RunReportRequest.newBuilder()
//                            .setProperty("properties/" + propertyId)
//                            // Dimensions
////                        .addDimensions(Dimension.newBuilder().setName("country"))
////                        .addDimensions(Dimension.newBuilder().setName("region"))
//                            .addDimensions(Dimension.newBuilder().setName("city"))
////                        .addDimensions(Dimension.newBuilder().setName("date"))
////                        .addDimensions(Dimension.newBuilder().setName("dateHour"))
////                        .addDimensions(Dimension.newBuilder().setName("dateHourMinute"))
//                            // Metrics
////                        .addMetrics(Metric.newBuilder().setName("activeUsers"))
////                        .addMetrics(Metric.newBuilder().setName("newUsers"))
//                            .addMetrics(Metric.newBuilder().setName("totalUsers"))
//                            .addDateRanges(DateRange.newBuilder().setStartDate("2024-01-28").setEndDate("today"))
//                            .build();
//            RunReportResponse allVisit = analyticsData.runReport(request2);
//            String allVisitors = allVisit.getRows(0).getMetricValues(0).getValue();
//            ga4Vo.setAllVisitors(allVisitors);
//        }catch(Exception e) {
//            e.printStackTrace();
//        }
//        return ga4Vo;
//    }
//}
