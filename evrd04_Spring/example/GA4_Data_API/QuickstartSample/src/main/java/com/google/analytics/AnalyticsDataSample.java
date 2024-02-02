package com.google.analytics;

import com.google.analytics.data.v1beta.BetaAnalyticsDataClient;
import com.google.analytics.data.v1beta.DateRange;
import com.google.analytics.data.v1beta.Metric;
import com.google.analytics.data.v1beta.RunReportRequest;
import com.google.analytics.data.v1beta.RunReportResponse;

public class AnalyticsDataSample {

    public static void main(String[] args) throws Exception {
        // Initialize the client using try-with-resources to manage resources automatically
        try (BetaAnalyticsDataClient analyticsData = BetaAnalyticsDataClient.create()) {
            // Create a request to get report data
            RunReportRequest request = RunReportRequest.newBuilder()
//                    .setProperty("properties/YOUR_PROPERTY_ID") // Replace with your GA4 Property ID
                    .setProperty("properties/425548737") // Replace with your GA4 Property ID
                    .addDateRanges(DateRange.newBuilder().setStartDate("2024-01-01").setEndDate("today").build())
                    .addMetrics(Metric.newBuilder().setName("activeUsers").build())
                    .build();

            // Call the API
            RunReportResponse response = analyticsData.runReport(request);

            // Process the response
            System.out.println("===============================================================");
            System.out.println("Report result:");
            System.out.println(response);
        }
    }
}
