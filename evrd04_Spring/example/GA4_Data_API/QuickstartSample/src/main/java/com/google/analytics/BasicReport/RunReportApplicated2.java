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
public class RunReportApplicated2 { // 보고서 객체 생성하는 RunReportRequest 오브젝트(Object)

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
            RunReportRequest request1 =
                    RunReportRequest.newBuilder()
                            .setProperty("properties/" + propertyId)
                            // Dimensions
                        .addDimensions(Dimension.newBuilder().setName("country"))
//                        .addDimensions(Dimension.newBuilder().setName("region"))
//                            .addDimensions(Dimension.newBuilder().setName("city"))
//                        .addDimensions(Dimension.newBuilder().setName("date"))
//                        .addDimensions(Dimension.newBuilder().setName("dateHour"))
//                        .addDimensions(Dimension.newBuilder().setName("dateHourMinute"))
                            // Metrics
                        .addMetrics(Metric.newBuilder().setName("activeUsers"))
//                        .addMetrics(Metric.newBuilder().setName("newUsers"))
//                            .addMetrics(Metric.newBuilder().setName("totalUsers"))
                            .addDateRanges(DateRange.newBuilder().setStartDate("today").setEndDate("today"))
                            .build();

            RunReportRequest request2 =
                    RunReportRequest.newBuilder()
                            .setProperty("properties/" + propertyId)
                            // Dimensions
                        .addDimensions(Dimension.newBuilder().setName("country"))
//                        .addDimensions(Dimension.newBuilder().setName("region"))
//                            .addDimensions(Dimension.newBuilder().setName("city"))
//                        .addDimensions(Dimension.newBuilder().setName("date"))
//                        .addDimensions(Dimension.newBuilder().setName("dateHour"))
//                        .addDimensions(Dimension.newBuilder().setName("dateHourMinute"))
                            // Metrics
                        .addMetrics(Metric.newBuilder().setName("activeUsers"))
//                        .addMetrics(Metric.newBuilder().setName("newUsers"))
//                            .addMetrics(Metric.newBuilder().setName("totalUsers"))
                            .addDateRanges(DateRange.newBuilder().setStartDate("2024-01-09").setEndDate("today"))
                            .build();

            RunReportResponse todayVisit = analyticsData.runReport(request1);
            RunReportResponse allVisit = analyticsData.runReport(request2);

            System.out.println("------------------------------------");
//            System.out.println(todayVisit.getMaximumsCount()); // 0 // allvisit도 0 나옴
//            todayVisit.getMaximums(0);
//            System.out.println(todayVisit.getKind()); // analyticsData#runReport
//            System.out.println(todayVisit.getMetadata()); // currency_code: "KRW" // time_zone: "Asia/Seoul"
////            todayVisit.getMetricHeaders();
//            System.out.println(todayVisit.getMetricHeadersList()); // [name: "totalUsers" type: TYPE_INTEGER]
//            System.out.println(todayVisit.getMetricHeadersCount()); // 1
//            System.out.println(todayVisit.getDimensionHeadersOrBuilderList()); // [name: "city"]
//            System.out.println(todayVisit.getPropertyQuota()); // "" 나옴
//            System.out.println(todayVisit.getAllFields());   // 있는 모든 속성 표출
//            System.out.println(todayVisit.getTotalsList()); // []
            System.out.println("------------------------------------");


//            if(todayVisit.getRows(0).getMetricValues(0).getValue().isEmpty() ||
//               todayVisit.getRows(0).getMetricValues(0).getValue().equals("0")
//                ){
//                System.out.println("null");
//            }

            String todayVisitors = todayVisit.getRows(0).getMetricValues(0).getValue();
            String allVisitors = allVisit.getRows(0).getMetricValues(0).getValue();

            System.out.println("====================================================================");

//            System.out.println("todayVisitors : "+ todayVisitors);
//            System.out.println("allVisitors : "+ allVisitors);

            // print result
//            printout(response);
            System.out.println("====================================================================");
            System.out.println("<<<todayVisitor>>> : "+"\n" + todayVisit);
            System.out.println("====================================================================");
            System.out.println("<<<allVisitor>>> : "+"\n" + allVisit);

            System.out.println("====================================================================");
            Row row1 = todayVisit.getRows(0);
            Row row2 = allVisit.getRows(0);
            System.out.println("row1 : " + row1);
            System.out.println("row2 : " + row2);
            System.out.println("row1 : " + row1.getMetricValues(0));            // value: "1"   <- .getValue() 안붙이면 이렇게 나옴
            System.out.println("row2 : " + row2.getMetricValues(0));            // value: "6"
            System.out.println("row1 : " + row1.getMetricValues(0).getValue()); // 1
            System.out.println("row2 : " + row2.getMetricValues(0).getValue()); // 6

//            printoutApplicated(response1);
//            printoutApplicated(response2);
        }
    }

}