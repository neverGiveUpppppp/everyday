package com.google.analytics.BasicReport;

import com.google.analytics.data.v1beta.*;

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
 * mvn compile exec:java -Dexec.mainClass="com.google.analytics.data.samples.RunReportSample"
 * }</pre>
 */
public class RunReportTesting { // 보고서 객체 생성하는 RunReportRequest 오브젝트(Object)

    public static void main(String... args) throws Exception {
        /**
         * TODO(developer): Replace this variable with your Google Analytics 4 property ID before
         * running the sample.
         */
        String propertyId = "425548737";
        runReport(propertyId);
    }

    // Runs a report of active users grouped by country.
    static void runReport(String propertyId) throws Exception {

        // Using a default constructor instructs the client to use the credentials
        // specified in GOOGLE_APPLICATION_CREDENTIALS environment variable.
        try (BetaAnalyticsDataClient analyticsData = BetaAnalyticsDataClient.create()) {
            RunReportRequest request =
                    RunReportRequest.newBuilder()
                            .setProperty("properties/" + propertyId)
                            // Dimensions
//                            .addDimensions(Dimension.newBuilder().setName("country"))
//                            .addDimensions(Dimension.newBuilder().setName("region"))
//                            .addDimensions(Dimension.newBuilder().setName("city"))
                            .addDimensions(Dimension.newBuilder().setName("date"))
//                            .addDimensions(Dimension.newBuilder().setName("dateHour"))
//                            .addDimensions(Dimension.newBuilder().setName("dateHourMinute"))
                            // Metrics
                            .addMetrics(Metric.newBuilder().setName("activeUsers"))
                            .addMetrics(Metric.newBuilder().setName("newUsers"))
                            .addMetrics(Metric.newBuilder().setName("totalUsers"))
//                            .addDateRanges(DateRange.newBuilder().setStartDate("today").setEndDate("today"))  // 결과x // 수십분? 지나니 5일 결과 나옴.
//                            .addDateRanges(DateRange.newBuilder().setStartDate("1daysAgo").setEndDate("today"))  // 결과x // 수십분? 지나니 5일 결과 나옴.
//                            .addDateRanges(DateRange.newBuilder().setStartDate("yesterday").setEndDate("today")) // 결과x // 같은 day기준 조회인데 몇일이냐에 따라 반영차이가 남
//                            .addDateRanges(DateRange.newBuilder().setStartDate("2daysAgo").setEndDate("today"))  // 3,5일 데이터 나옴.
//                            .addDateRanges(DateRange.newBuilder().setStartDate("3daysAgo").setEndDate("today"))  // 2,3일 데이터만 나옴. 4일꺼는 안나옴
                            .addDateRanges(DateRange.newBuilder().setStartDate("7daysAgo").setEndDate("today"))  // 1,2,3,5일 데이터 나옴
//                            .addDateRanges(DateRange.newBuilder().setStartDate("2024-01-28").setEndDate("today"))  //
                            .build();

            // Make the request.
            RunReportResponse response = analyticsData.runReport(request);
            // print result
            printout(response);
        }
    }

    // Prints results of a runReport call.
    static void printout(RunReportResponse response){
        System.out.printf("%s rows received%n", response.getRowsList().size());

        for (DimensionHeader header : response.getDimensionHeadersList()) {
            System.out.printf("Dimension header name: %s%n", header.getName());
        }

        for (MetricHeader header : response.getMetricHeadersList()) {
            System.out.printf("Metric header name: %s (%s)%n", header.getName(), header.getType());
        }

        System.out.println("====================================================================");
        System.out.println("Report result:");
        System.out.println("response.getRowsCount() :" + response.getRowsCount());
        int dimensionsCnt = 0;
        int metricsCnt = 0;
        for (Row row : response.getRowsList()) {
            for(int i=0; i < row.getDimensionValuesCount(); i++ ) {
                System.out.printf("Dimension[%d] : " + row.getDimensionValues(i).getValue() +"\n", i);
            }
            for(int j=0; j < row.getMetricValuesCount(); j++ ) {
                System.out.printf("Metrics[%d] : " + row.getMetricValues(j).getValue() +"\n", j);
            }
        }



        // original
//        for (Row row : response.getRowsList()) {
//            System.out.printf( // dimension 및 metric 개수에 따라 index수 조정해야함
//                "%s, %s%n", row.getDimensionValues(row.getDimensionValuesCount()-1).getValue()
//                          , row.getMetricValues(row.getMetricValuesCount()-1).getValue()
//            );
//        }
    }
}