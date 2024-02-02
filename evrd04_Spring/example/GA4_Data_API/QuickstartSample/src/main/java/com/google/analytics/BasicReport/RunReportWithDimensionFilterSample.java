package com.google.analytics.BasicReport;


import com.google.analytics.data.v1beta.BetaAnalyticsDataClient;
import com.google.analytics.data.v1beta.DateRange;
import com.google.analytics.data.v1beta.Dimension;
import com.google.analytics.data.v1beta.Filter;
import com.google.analytics.data.v1beta.FilterExpression;
import com.google.analytics.data.v1beta.Metric;
import com.google.analytics.data.v1beta.RunReportRequest;
import com.google.analytics.data.v1beta.RunReportResponse;

/**
 * Google Analytics Data API sample application demonstrating the usage of dimension and metric
 * filters in a report.
 *
 * <p>See
 * https://developers.google.com/analytics/devguides/reporting/data/v1/rest/v1beta/properties/runReport#body.request_body.FIELDS.dimension_filter
 * for more information.
 *
 * <p>Before you start the application, please review the comments starting with "TODO(developer)"
 * and update the code to use correct values.
 *
 * <p>To run this sample using Maven:
 *
 * <pre>{@code
 * cd google-analytics-data
 * mvn compile exec:java -Dexec.mainClass="com.google.analytics.data.samples.RunReportWithDimensionFilterSample"
 * }</pre>
 */
public class RunReportWithDimensionFilterSample { // 특정 측정기준 값에 대한 데이터만 사용하여 보고서를 생성

    public static void main(String... args) throws Exception {
        // TODO(developer): Replace with your Google Analytics 4 property ID before running the sample.
        String propertyId = "425548737";
        sampleRunReportWithDimensionFilter(propertyId);
    }

    // Runs a report using a dimension filter. The call returns a time series report of `eventCount`
    // when `eventName` is `first_open` for each date.
    // This sample uses relative date range values.
    // See https://developers.google.com/analytics/devguides/reporting/data/v1/rest/v1beta/DateRange
    // for more information.
    static void sampleRunReportWithDimensionFilter(String propertyId) throws Exception {
        // Initialize client that will be used to send requests. This client only needs to be created
        // once, and can be reused for multiple requests. After completing all of your requests, call
        // the "close" method on the client to safely clean up any remaining background resources.
        try (BetaAnalyticsDataClient analyticsData = BetaAnalyticsDataClient.create()) {
            RunReportRequest request =
                    RunReportRequest.newBuilder()
                            .setProperty("properties/" + propertyId)
                            .addDimensions(Dimension.newBuilder().setName("date"))
                            .addMetrics(Metric.newBuilder().setName("eventCount"))
                            .addDateRanges(
                                    DateRange.newBuilder().setStartDate("7daysAgo").setEndDate("yesterday"))
                            .setDimensionFilter(
                                    FilterExpression.newBuilder()
                                            .setFilter(
                                                    Filter.newBuilder()
//                                                            .setFieldName("eventName")
//                                                            .setStringFilter(
//                                                                    Filter.StringFilter.newBuilder().setValue("first_open"))))
//                            .build();
                                                            .setFieldName("achievementId")
                                                            .setStringFilter(
                                                                    Filter.StringFilter.newBuilder().setValue("activeUsers"))))
                            .build();


            // Make the request.
            RunReportResponse response = analyticsData.runReport(request);
            // Prints the response using a method in RunReportSample.java
            RunReportSample.printRunResponseResponse(response);
        }
    }
}
