package com.google.analytics4.BasicReport;


import com.google.analytics.data.v1beta.BetaAnalyticsDataClient;
import com.google.analytics.data.v1beta.DateRange;
import com.google.analytics.data.v1beta.Dimension;
import com.google.analytics.data.v1beta.Metric;
import com.google.analytics.data.v1beta.RunReportRequest;
import com.google.analytics.data.v1beta.RunReportResponse;

/**
 * Google Analytics Data API sample application demonstrating the creation of a basic report.
 *
 * <p>See
 * https://developers.google.com/analytics/devguides/reporting/data/v1/rest/v1beta/properties/runReport
 * for more information.
 *
 * <p>Before you start the application, please review the comments starting with "TODO(developer)"
 * and update the code to use correct values.
 *
 * <p>To run this sample using Maven:
 *
 * <pre>{@code
 * cd google-analytics-data
 * mvn compile exec:java -Dexec.mainClass="com.google.analytics.data.samples.RunReportWithMultipleDimensionsSample"
 * }</pre>
 */
public class RunReportWithMultipleDimensionsSample { // dimension 여러개에 metrics 한 개

    public static void main(String... args) throws Exception {
        // TODO(developer): Replace with your Google Analytics 4 property ID before running the sample.
        String propertyId = "425548737";
        sampleRunReportWithMultipleDimensions(propertyId);
    }

    // Runs a report of active users grouped by three dimensions.
    static void sampleRunReportWithMultipleDimensions(String propertyId) throws Exception {
        // Initialize client that will be used to send requests. This client only needs to be created
        // once, and can be reused for multiple requests. After completing all of your requests, call
        // the "close" method on the client to safely clean up any remaining background resources.
        try (BetaAnalyticsDataClient analyticsData = BetaAnalyticsDataClient.create()) {
            RunReportRequest request =
                    RunReportRequest.newBuilder()
                            .setProperty("properties/" + propertyId)
                            .addDimensions(Dimension.newBuilder().setName("country"))
                            .addDimensions(Dimension.newBuilder().setName("region"))
                            .addDimensions(Dimension.newBuilder().setName("city"))
                            .addMetrics(Metric.newBuilder().setName("activeUsers"))
                            .addDateRanges(DateRange.newBuilder().setStartDate("7daysAgo").setEndDate("today"))
                            .build();

            // Make the request.
            RunReportResponse response = analyticsData.runReport(request);
            // Prints the response using a method in RunReportSample.java
            RunReportSample.printRunResponseResponse(response);
        }
    }
}