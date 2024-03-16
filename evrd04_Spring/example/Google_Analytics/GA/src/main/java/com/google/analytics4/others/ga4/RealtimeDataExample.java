package com.google.analytics4.others.ga4;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.services.analyticsreporting.v4.AnalyticsReporting;
import com.google.api.services.analyticsreporting.v4.model.SearchUserActivityRequest;
import com.google.api.services.analyticsreporting.v4.model.SearchUserActivityResponse;


import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;

public class RealtimeDataExample {

    public static void main(String[] args) {
        try {
            // JSON 키 파일의 경로
            String keyFilePath = "D:\\settings\\firsttest-412405-9ce6f852e4dd.json";

            // Google Analytics 뷰 ID
//            String viewId = "your-view-id";
            String viewId = "6689665067";

            // 서비스 초기화
            AnalyticsReporting service = initializeAnalyticsReporting(keyFilePath);

            // 사용자 활동 데이터 검색
            searchUserActivity(service, viewId);

        } catch (IOException | GeneralSecurityException e) {
            e.printStackTrace();
        }
    }

    private static AnalyticsReporting initializeAnalyticsReporting(String keyFilePath) throws IOException, GeneralSecurityException {
        GoogleCredential credential = GoogleCredential.fromStream(new FileInputStream(keyFilePath))
                .createScoped(Arrays.asList("https://www.googleapis.com/auth/analytics.readonly"));

        return new AnalyticsReporting.Builder(credential.getTransport(), credential.getJsonFactory(), null)
                .setApplicationName("Your-Application-Name")
                .build();
    }

    private static void searchUserActivity(AnalyticsReporting service, String viewId) throws IOException {
        // 사용자 활동 데이터 검색 요청 설정
        SearchUserActivityRequest request = new SearchUserActivityRequest();
        request.setViewId(viewId);
//        request.setUser("ga:123456");  // 사용자 식별자 설정
//        request.setUser("300387543");  // 사용자 식별자 설정

        // 요청 및 응답 처리
        try {
            SearchUserActivityResponse response = service.userActivity().search(request).execute();

            // 응답 데이터 출력
            System.out.println(response);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

