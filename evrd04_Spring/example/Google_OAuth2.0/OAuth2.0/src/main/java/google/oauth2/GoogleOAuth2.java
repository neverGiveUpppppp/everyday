package google.oauth2;

import com.google.analytics.data.v1beta.RunReportResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



@RestController

public class GoogleOAuth2 {


    @Autowired
    private RestTemplate restTemplate;

    private static final String ANALYTICS_URL = "https://analyticsdata.googleapis.com/v1beta/properties/425548737:runReport";
    private static final String REFRESH_TOKEN_URL = "https://oauth2.googleapis.com/token";
    private static final String INITIAL_ACCESS_TOKEN = "ya29.a0N3_BWFO5Fw-DWVl8Erqt_iqBLAsbZ_oISr_ym-1Y1II-0klxOYTZHOfmYWKEHKa";


    // HttpHeaders랑 Map으로 body부분을 만들어서 HttpEntity객체에 넣고 request할 객체 생성
    @RequestMapping("/oauth2")
    public void makeAnalyticsRequest(String accessToken) {  // 액세스 토큰을 사용하여 Google Analytics API 요청
        HttpHeaders headers = new HttpHeaders();            // HTTP 헤더 생성
        headers.setContentType(MediaType.APPLICATION_JSON); // 내용 타입을 JSON으로 설정
        headers.setBearerAuth(accessToken); // Bearer 토큰으로 인증 헤더 설정

        Map<String, Object> requestBody = new HashMap<>(); // 요청 본문(body)을 위한 맵 생성
        // 요청 본문에 포함될 데이터 설정
        requestBody.put("dimensions", new Object[]{new HashMap<String, Object>() {{
            put("name", "country");
        }}});
        requestBody.put("metrics", new Object[]{new HashMap<String, Object>() {{
            put("name", "activeUsers");
        }}});
        requestBody.put("dateRanges", new Object[]{new HashMap<String, Object>() {{
            put("startDate", "2024-01-01");
            put("endDate", "2024-03-05");
        }}});

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers); // 요청 엔티티 생성

        try {
//            ResponseEntity<RunReportResponse> responseEntity = restTemplate.postForEntity(ANALYTICS_URL, entity, RunReportResponse.class); // org.springframework.web.client.UnknownContentTypeException: Could not extract response: no suitable HttpMessageConverter found for response type [class com.google.analytics.data.v1beta.RunReportResponse] and content type [application/json;charset=UTF-8]
//            RunReportResponse response = responseEntity.getBody();
//            String cnt = response.getRows(0).getMetricValues(0).getValue();
//            RunReportResponse reportResponse = (RunReportResponse)response.getBody(); // inconvertable
            ResponseEntity<String> response = restTemplate.postForEntity(ANALYTICS_URL, entity, String.class); // POST 요청 실행
//            System.out.println(response.getBody()); // 응답 본문 출력
            handleAnalyticsResponse(response.getBody());
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) { // 액세스 토큰이 만료된 경우
                refreshTokenAndRetryRequest(); // 토큰 새로고침 후 요청 재시도
            } else {
                throw e; // 다른 HTTP 에러 발생 시 예외 던짐
            }
        }
    }

    public void refreshTokenAndRetryRequest() { // 액세스 토큰 새로고침 후 요청 재시도
        HttpHeaders headers = new HttpHeaders(); // HTTP 헤더 생성
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED); // 내용 타입을 Form-Urlencoded로 설정

        MultiValueMap<String, String> map= new LinkedMultiValueMap<>(); // 토큰 새로고침 요청 본문 데이터
        // OAuth2 클라이언트 정보와 새로고침 토큰 설정
        map.add("client_id", "722057675499-t3vsgfeak2ljk0o71351hbufu90thhij.apps.googleusercontent.com");
        map.add("client_secret", "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");
        map.add("grant_type", "refresh_token");
        map.add("refresh_token", "1//0edI4ei8A3OIoCgYIARAAGA4SNwF-L9IrcMTFQz-mFr12QrVJ2XjSaYFzw0zfs0zmSwjNptFCSZKQJC0r6mzyGk2xlkkygY4nMs8");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers); // 요청 엔티티 생성

        ResponseEntity<String> response = restTemplate.postForEntity(REFRESH_TOKEN_URL, request , String.class); // 토큰 새로고침 요청 실행

        try {
            ObjectMapper objectMapper = new ObjectMapper();     // JSON 문자열을 객체로 변환하기 위한 ObjectMapper
            Map<String, String> result = objectMapper.readValue(response.getBody(), Map.class); // 응답 본문을 Map으로 변환
            String newAccessToken = result.get("access_token"); // 새 액세스 토큰 추출
            makeAnalyticsRequest(newAccessToken);               // 새 토큰으로 요청 재시도
        } catch (Exception e) {
            e.printStackTrace();    // 예외 정보 출력
            throw new RuntimeException("Refresh token 요청 실패", e); // 예외 던짐
        }
    }

    // response.getBody()의 파싱용 메소드
    public void handleAnalyticsResponse(String responseBody) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> responseMap = objectMapper.readValue(responseBody, Map.class);

            // 예시 응답 구조에 따라 경로를 조정해야 할 수 있습니다.
            List<Map<String, Object>> rows = (List<Map<String, Object>>) responseMap.get("rows");
            if (rows != null && !rows.isEmpty()) {
                // 첫 번째 'row'의 'metricValues' 가져오기
                List<Map<String, String>> metricValues = (List<Map<String, String>>) rows.get(0).get("metricValues");

                if (metricValues != null && !metricValues.isEmpty()) {
                    // 첫 번째 메트릭 값의 'value' 추출
                    String visitorsCount = metricValues.get(0).get("value");
                    System.out.println("방문자 수: " + visitorsCount);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("응답 처리 중 오류 발생", e);
        }
    }
    
    
    public String visitors(String body, Model model){


        return "helloAjax";
    }

}
