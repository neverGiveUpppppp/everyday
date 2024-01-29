package com.google.analytics;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.api.services.analyticsreporting.v4.AnalyticsReportingScopes;
import com.google.api.services.analyticsreporting.v4.AnalyticsReporting;

import com.google.api.services.analyticsreporting.v4.model.ColumnHeader;
import com.google.api.services.analyticsreporting.v4.model.DateRange;
import com.google.api.services.analyticsreporting.v4.model.DateRangeValues;
import com.google.api.services.analyticsreporting.v4.model.GetReportsRequest;
import com.google.api.services.analyticsreporting.v4.model.GetReportsResponse;
import com.google.api.services.analyticsreporting.v4.model.Metric;
import com.google.api.services.analyticsreporting.v4.model.Dimension;
import com.google.api.services.analyticsreporting.v4.model.MetricHeaderEntry;
import com.google.api.services.analyticsreporting.v4.model.Report;
import com.google.api.services.analyticsreporting.v4.model.ReportRequest;
import com.google.api.services.analyticsreporting.v4.model.ReportRow;


// Google Analytics Reporting API V4를 사용하여 Google Analytics 데이터를 조회하고 결과를 출력
// 특정 뷰에서 지정된 날짜 범위와 메트릭에 대한 데이터를 조회하고, 결과를 콘솔에 출력하는 Java 애플리케이션의 기본적인 구조를 보여줍니다.
public class HelloAnalyticsReporting {

    // 애플리케이션 이름과 JSON 팩토리 초기화
    private static final String APPLICATION_NAME = "Hello Analytics Reporting";
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    // 사용자의 서비스 계정 키 파일 위치와 GA 뷰 ID
//    private static final String KEY_FILE_LOCATION = "D:\\my\\everyday\\evrd04_Spring\\example\\GA4_sam\\firsttest-412405-9ce6f852e4dd.json"; // com.google.api.client.googleapis.json.GoogleJsonResponseException: 403 Forbidden
    private static final String KEY_FILE_LOCATION = "firsttest-412405-9ce6f852e4dd.json";  // com.google.api.client.googleapis.json.GoogleJsonResponseException: 403 Forbidden
    private static final String VIEW_ID = "6676412145";



    public static void main(String[] args) {
        try {
            AnalyticsReporting service = initializeAnalyticsReporting();

            GetReportsResponse response = getReport(service);
            printResponse(response);
        } catch (Exception e) {
            e.printStackTrace();git re
        }
    }

    /**
     * Initializes an Analytics Reporting API V4 service object.
     *
     * @return An authorized Analytics Reporting API V4 service object.
     * @throws IOException
     * @throws GeneralSecurityException
     */ // 아래 메소드 역할 : HTTP 트랜스포트, JSON 팩토리, 인증 정보를 사용하여 Analytics Reporting API 서비스 객체를 초기화하고 반환함
    private static AnalyticsReporting initializeAnalyticsReporting() throws GeneralSecurityException, IOException {

        // 네트워크 전송 및 GoogleCredential를 사용해 OAuth2 인증
        HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        GoogleCredential credential = GoogleCredential
                .fromStream(new FileInputStream(KEY_FILE_LOCATION))
                .createScoped(AnalyticsReportingScopes.all());

        // Analytics Reporting 서비스 객체 생성 및 반환
        // Construct the Analytics Reporting service object.
        return new AnalyticsReporting.Builder(httpTransport, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME).build();
    }

    /**
     * Queries the Analytics Reporting API V4.
     *
     * @param service An authorized Analytics Reporting API V4 service object.
     * @return GetReportResponse The Analytics Reporting API V4 response.
     * @throws IOException
     */ // 아래 메소드 역할 :  날짜 범위, 측정할 메트릭(예: 세션 수), 차원Dimension(예: 페이지 타이틀)을 설정하고 이를 포함한 리포트 요청을 실행
    private static GetReportsResponse getReport(AnalyticsReporting service) throws IOException {
        // 날짜 범위 설정 Create the DateRange object.
        DateRange dateRange = new DateRange();
        dateRange.setStartDate("7DaysAgo");
        dateRange.setEndDate("today");

        // 측정할 메트릭 설정 Create the Metrics object.
        Metric sessions = new Metric()
                .setExpression("ga:sessions")
                .setAlias("sessions");

        // 측정할 차원Dimension 설정
        Dimension pageTitle = new Dimension().setName("ga:pageTitle");

        // 리포트 요청 객체 생성 Create the ReportRequest object.
        ReportRequest request = new ReportRequest()
                .setViewId(VIEW_ID)
                .setDateRanges(Arrays.asList(dateRange))
                .setMetrics(Arrays.asList(sessions))
                .setDimensions(Arrays.asList(pageTitle));

        ArrayList<ReportRequest> requests = new ArrayList<ReportRequest>();
        requests.add(request);

        // 리포트 요청 실행 및 응답 반환
        // Create the GetReportsRequest object.
        GetReportsRequest getReport = new GetReportsRequest()
                .setReportRequests(requests);

        // Call the batchGet method.
        GetReportsResponse response = service.reports().batchGet(getReport).execute();

        return response;
    }

    /**
     * Parses and prints the Analytics Reporting API V4 response.
     *
     * @param response An Analytics Reporting API V4 response.
     */
    //  API 응답을 분석하여 리포트의 결과를 콘솔에 출력
    // 각 리포트에 대해 차원(예: 페이지 타이틀)과 메트릭(예: 세션 수) 값을 출력
    private static void printResponse(GetReportsResponse response) {

        // 헤더와 데이터 행 처리
        for (Report report: response.getReports()) {
            ColumnHeader header = report.getColumnHeader();
            List<String> dimensionHeaders = header.getDimensions();
            List<MetricHeaderEntry> metricHeaders = header.getMetricHeader().getMetricHeaderEntries();
            List<ReportRow> rows = report.getData().getRows();

            if (rows == null) {
                System.out.println("No data found for " + VIEW_ID);
                return;
            }

            for (ReportRow row: rows) {
                List<String> dimensions = row.getDimensions();
                List<DateRangeValues> metrics = row.getMetrics();

                for (int i = 0; i < dimensionHeaders.size() && i < dimensions.size(); i++) {
                    System.out.println(dimensionHeaders.get(i) + ": " + dimensions.get(i));
                }

                for (int j = 0; j < metrics.size(); j++) {
                    System.out.print("Date Range (" + j + "): ");
                    DateRangeValues values = metrics.get(j);
                    for (int k = 0; k < values.getValues().size() && k < metricHeaders.size(); k++) {
                        System.out.println(metricHeaders.get(k).getName() + ": " + values.getValues().get(k));
                    }
                }
            }
        }
    }
}