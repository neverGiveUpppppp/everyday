package com.google.analytics;

import com.google.analytics.data.v1beta.*;

public class AnalyticsDataSample2 {

    public static void main(String[] args) throws Exception {
        // Initialize the client using try-with-resources to manage resources automatically
        try (BetaAnalyticsDataClient analyticsData = BetaAnalyticsDataClient.create()) {
            // Create a request to get report data
            RunReportRequest requestToday = RunReportRequest.newBuilder()
                    .setProperty("properties/425548737") // Replace with your GA4 Property ID
                    .addDateRanges(DateRange.newBuilder().setStartDate("today").setEndDate("today").build())
                    .addMetrics(Metric.newBuilder().setName("activeUsers").build())
                    .addMetrics(Metric.newBuilder().setName("active1DayUsers").build())
                    .build();

            RunReportRequest requestTotal = RunReportRequest.newBuilder()
                    .setProperty("properties/425548737")
//                    .addDateRanges(DateRange.newBuilder().setStartDate("2024-01-26").setEndDate("today").build())
                    .addDateRanges(DateRange.newBuilder().setStartDate("5daysAgo").setEndDate("today").build())
                    .addMetrics(Metric.newBuilder().setName("activeUsers").build())
                    .build();

            RunReportResponse responseToday = analyticsData.runReport(requestToday);
            RunReportResponse responseTotal = analyticsData.runReport(requestTotal);

            // Assuming the first value in the metric list is the active users
            long todayUsers = responseToday.getRowCount() > 0 ? Long.parseLong(responseToday.getRows(0).getMetricValues(0).getValue()) : 0;
            long totalUsers = responseTotal.getRowCount() > 0 ? Long.parseLong(responseTotal.getRows(0).getMetricValues(0).getValue()) : 0;

            System.out.println("responseToday : " + responseToday);
            System.out.println("Today's active users: " + todayUsers);
            System.out.println("Total active users: " + totalUsers);


//            // Call the API
//            RunReportResponse response = analyticsData.runReport(request);
//
//            // Process the response
//            System.out.println("===============================================================");
//            System.out.println("Report result:");
//            System.out.println(response);
        }
    }
}
