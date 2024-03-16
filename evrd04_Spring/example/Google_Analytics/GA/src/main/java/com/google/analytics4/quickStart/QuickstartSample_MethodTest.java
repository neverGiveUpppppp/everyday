package com.google.analytics4.quickStart;

/*
 * Copyright 2022 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


// [START analyticsdata_quickstart]
import com.google.analytics.data.v1beta.BetaAnalyticsDataClient;
import com.google.analytics.data.v1beta.DateRange;
import com.google.analytics.data.v1beta.Dimension;
import com.google.analytics.data.v1beta.Metric;
import com.google.analytics.data.v1beta.RunReportRequest;
import com.google.analytics.data.v1beta.RunReportResponse;

/**
 * Google Analytics Data API sample quickstart application.
 *
 * <p>This application demonstrates the usage of the Analytics Data API using service account
 * credentials.
 *
 * <p>Before you start the application, please review the comments starting with "TODO(developer)"
 * and update the code to use correct values.
 *
 * <p>To run this sample using Maven:
 *
 * <pre>{@code
 * cd google-analytics-data
 * mvn compile exec:java -Dexec.mainClass="com.google.analytics.data.samples.QuickstartSample"
 * }</pre>
 */
public class QuickstartSample_MethodTest {

    public static void main(String... args) throws Exception {
        /**
         * TODO(developer): Replace this variable with your Google Analytics 4 property ID before
         * running the sample.
         */
//        String propertyId = "YOUR-GA4-PROPERTY-ID";
        String propertyId = "425548737";
        sampleRunReport(propertyId);
    }

    // This is an example snippet that calls the Google Analytics Data API and runs a simple report
    // on the provided GA4 property id.
    static void sampleRunReport(String propertyId) throws Exception {
        // [START analyticsdata_initialize]
        // Using a default constructor instructs the client to use the credentials
        // specified in GOOGLE_APPLICATION_CREDENTIALS environment variable.
        try (BetaAnalyticsDataClient analyticsData = BetaAnalyticsDataClient.create()) {
            // [END analyticsdata_initialize]

            // [START analyticsdata_run_report]
            RunReportRequest request =
                    RunReportRequest.newBuilder()
                            .setProperty("properties/" + propertyId)
                            .addDimensions(Dimension.newBuilder().setName("city"))
                            .addMetrics(Metric.newBuilder().setName("activeUsers"))
                            .addMetrics(Metric.newBuilder().setName("active1DayUsers").build())
                            .addDateRanges(DateRange.newBuilder().setStartDate("2020-03-31").setEndDate("today"))

                            .addDimensions(Dimension.newBuilder().setName("country"))
                            .addMetrics(Metric.newBuilder().setName("activeUsers"))
                            .addDateRanges(DateRange.newBuilder().setStartDate("2020-09-01").setEndDate("2020-09-15"))
                            .build();

            // Make the request.
            RunReportResponse response = analyticsData.runReport(request);
            // [END analyticsdata_run_report]

            // [START analyticsdata_print_report]
            System.out.println("====================================================");
            System.out.println("Report result:");
            System.out.println("====================================================");
            System.out.println("response.getMaximumsCount() : " + response.getMaximumsCount() + "\n"); // 0
            System.out.println(response.getDimensionHeaders(0)); // name: "city"
            System.out.println(response.getDimensionHeadersCount());   // 1
            System.out.println(response.getDimensionHeadersList());    // [name: "city"]
            System.out.println(response.getDimensionHeadersOrBuilder(0)); // name: "city"
            System.out.println(response.getDimensionHeadersOrBuilderList());    // [name: "city"]

            System.out.println("====================================================");
//            System.out.println("getDefaultInstanceForType() : " + response.getDefaultInstanceForType()); // (값없음)
//            System.out.println(response.getMetricHeaders(0));      // name: "activeUsers" // type: TYPE_INTEGER
//            System.out.println(response.getMetricHeadersCount());        // 2
//            System.out.println(response.getMetricHeadersList());         // [name: "activeUsers" type: TYPE_INTEGER, name: "active1DayUsers" type: TYPE_INTEGER]
//            System.out.println(response.getMetricHeadersOrBuilderList());// [name: "activeUsers" type: TYPE_INTEGER, name: "active1DayUsers" type: TYPE_INTEGER]
//
//            System.out.println(response.getKind());              // analyticsData#runReport
//            System.out.println(response.getKindBytes());         // <ByteString@a7e2d9d size=23 contents="analyticsData#runReport">
//            System.out.println(response.getMetadata());          // currency_code: "KRW" / time_zone: "Asia/Seoul"
//            System.out.println(response.getMetadataOrBuilder()); // currency_code: "KRW" / time_zone: "Asia/Seoul"
//
//            System.out.println(response.getMaximums(0));        // IndexOutOfBoundsException
//            System.out.println(response.getMaximumsCount());          // 0
//            System.out.println(response.getMaximumsList());           // []
//            System.out.println(response.getMaximumsOrBuilder(0)); // IndexOutOfBoundsException
//            System.out.println(response.getMaximumsOrBuilderList());  // []


//            System.out.println("====================================================");
//            // Iterate through every row of the API response.
//            for (Row row : response.getRowsList()) {
////                System.out.println("row.getDimensionValues(1) : " + row.getDimensionValues(1).getValue()); // error : IndexOutOfBoundsException
//                System.out.println("getMetricValues(0) :                " + row.getMetricValues(0).getValue()); // 4
//                System.out.println("getMetricValues(1) :                " + row.getMetricValues(1).getValue()); // 4
//                System.out.println("getDimensionValuesCount() :         " + row.getDimensionValuesCount());           // 1  <- 디멘션 한 개 의미인 듯
//                System.out.println("getDimensionValuesList() :          " + row.getDimensionValuesList());            // [value: "Seoul"]  <- 위의 디멘션 설정 한 개 맞음
//                System.out.println("getDimensionValuesOrBuilderList() : " + row.getDimensionValuesOrBuilderList());   // [value: "Seoul"]
//                System.out.println("getDimensionValuesOrBuilder(0) :    " + row.getDimensionValuesOrBuilder(0));//  value: "Seoul"
//
//                System.out.println("getMetricValues(0) :                " + row.getMetricValues(0));       // value: "4"
//                System.out.println("getMetricValuesCount() :            " + row.getMetricValuesCount());         // 2
//                System.out.println("getMetricValuesList(0) :            " + row.getMetricValuesList());          // [value: "4", value: "4"]
//                System.out.println("getMetricValuesOrBuilder(0) :       " + row.getMetricValuesOrBuilder(0)); // value: "4"
//                System.out.println("getDimensionValuesOrBuilderList() : " + row.getDimensionValuesOrBuilderList()); // [value: "Seoul"]
//                System.out.println("getInitializationErrorString() :    " + row.getInitializationErrorString()); //
//
//
//                System.out.println("isInitialized() : " + row.isInitialized()); // true
////                System.out.println("getDimensionValuesOrBuilder(0) : " + row.getRepeatedField()); //
////                System.out.println("getDimensionValuesOrBuilder(0) : " + row.getRepeatedFieldCount()); //
//
//
//                System.out.println();
//                System.out.printf(
//                        "%s, %s%n", row.getDimensionValues(0).getValue(), row.getMetricValues(0).getValue());
//            }
            // [END analyticsdata_print_report]
        }
    }
}
// [END analyticsdata_quickstart]