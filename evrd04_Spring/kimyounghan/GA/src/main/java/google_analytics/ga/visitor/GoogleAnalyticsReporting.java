package testing;//package testing;
//
//import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
//import com.google.api.client.http.HttpTransport;
//import com.google.api.client.http.javanet.NetHttpTransport;
//import com.google.api.client.json.JsonFactory;
//import com.google.api.client.json.jackson2.JacksonFactory;
//import com.google.api.services.analyticsreporting.v4.AnalyticsReporting;
//import com.google.api.services.analyticsreporting.v4.AnalyticsReportingScopes;
//import com.google.api.services.analyticsreporting.v4.model.*;
//
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//public class GoogleAnalyticsReporting {
//
//    private static final String APPLICATION_NAME = "Google Analytics Reporting";
//    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
//    private static final String KEY_FILE_LOCATION = "[PATH_TO_YOUR_JSON_KEY_FILE]";
//    private static final String VIEW_ID = "[YOUR_VIEW_ID]";
//
//    public static void main(String[] args) {
//        try {
//            AnalyticsReporting service = initializeAnalyticsReporting();
//            GetReportsResponse response = getReport(service);
//            printResponse(response);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static AnalyticsReporting initializeAnalyticsReporting() throws IOException {
//        HttpTransport httpTransport = new NetHttpTransport();
//        GoogleCredential credential = GoogleCredential
//                .fromStream(new FileInputStream(KEY_FILE_LOCATION))
//                .createScoped(AnalyticsReportingScopes.all());
//
//        // Construct the Analytics Reporting service object.
//        return new AnalyticsReporting.Builder(httpTransport, JSON_FACTORY, credential)
//                .setApplicationName(APPLICATION_NAME)
//                .build();
//    }
//
//    private static GetReportsResponse getReport(AnalyticsReporting service) throws IOException {
//        // Create the DateRange object.
//        DateRange dateRange = new DateRange();
//        dateRange.setStartDate("7daysAgo");
//        dateRange.setEndDate("today");
//
//        // Create the Metrics object.
//        Metric sessions = new Metric()
//                .setExpression("ga:sessions")
//                .setAlias("sessions");
//
//        // Create the ReportRequest object.
//        ReportRequest request = new ReportRequest()
//                .setViewId(VIEW_ID)
//                .setDateRanges(Arrays.asList(dateRange))
//                .setMetrics(Arrays.asList(sessions));
//
//        ArrayList<ReportRequest> requests = new ArrayList<ReportRequest>();
//        requests.add(request);
//
//        // Create the GetReportsRequest object.
//        GetReportsRequest getReport = new GetReportsRequest()
//                .setReportRequests(requests);
//
//        // Call the batchGet method.
//        return service.reports().batchGet(getReport).execute();
//    }
//
//    private static void printResponse(GetReportsResponse response) {
//        for (Report report: response.getReports()) {
//            ColumnHeader header = report.getColumnHeader();
//            List<String> dimensionHeaders = header.getDimensions(); // getDimensionHeaders();
//            List<String> metricHeaders = header.getMetricHeader().getMetricHeaderEntries();
//
//            for (ReportRow row: report.getData().getRows()) {
//                List<String> dimensions = row.getDimensions();
//                List<DateRangeValues> metrics = row.getMetrics();
//
//                for (int i = 0; i < dimensionHeaders.size() && i < dimensions.size(); i++) {
//                    System.out.println(dimensionHeaders.get(i) + ": " + dimensions.get(i));
//                }
//
//                for (int j = 0; j < metrics.size(); j++) {
//                    System.out.print("Date Range (" + j + "): ");
//                    DateRangeValues values = metrics.get(j);
//                    for (int k = 0; k < values.getValues().size() && k < metricHeaders.size(); k++) {
//                        System.out.println(metricHeaders.get(k).getName() + ": " + values.getValues().get(k));
//                    }
//                }
//            }
//        }
//    }
//}