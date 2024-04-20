//package com.google.analytics3.visitor;
//
//import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
//import com.google.api.services.analyticsreporting.v4.AnalyticsReporting;
//import com.google.api.services.analyticsreporting.v4.AnalyticsReportingScopes;
//import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
//
//import java.io.FileInputStream;
//
//public class AnalyticsReportingService {
//    private static final String APPLICATION_NAME = "GA Reporting";
//    private static final String KEY_FILE_LOCATION = "D:\\my\\everyday\\evrd04_Spring\\kimyounghan\\spring02_core\\firsttest-412405-9ce6f852e4dd.json";
//
//    public static AnalyticsReporting initializeAnalyticsReporting() throws Exception {
//        GoogleCredential credential = GoogleCredential.fromStream(new FileInputStream(KEY_FILE_LOCATION))
//                .createScoped(AnalyticsReportingScopes.all());
//
//        return new AnalyticsReporting.Builder(
//                GoogleNetHttpTransport.newTrustedTransport(),
//                JacksonFactory.getDefaultInstance(),
//                credential)
//                .setApplicationName(APPLICATION_NAME)
//                .build();
//    }
//}
