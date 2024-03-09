package google.oauth2;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoogleAnalyticsServiceImpl implements GoogleAnalyticsService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public GoogleAnalyticsServiceImpl(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    private static final String ANALYTICS_URL = "https://analyticsdata.googleapis.com/v1beta/properties/425548737:runReport";
    private static final String REFRESH_TOKEN_URL = "https://oauth2.googleapis.com/token";



    public Map<String, Object> makeAnalyticsRequest(String accessToken) {
        // header 생성
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(accessToken);

        // body생성
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("dimensions", new Object[]{new HashMap<String, Object>() {{
            put("name", "country");
        }}});
        requestBody.put("metrics", new Object[]{new HashMap<String, Object>() {{
            put("name", "activeUsers");
        }}});
        requestBody.put("dateRanges", new Object[]{new HashMap<String, Object>() {{
            put("startDate", "2024-01-28");
            put("endDate", "today");
        }}});

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers); // reuqest 생성
        ResponseEntity<String> response = restTemplate.postForEntity(ANALYTICS_URL, entity, String.class);

        if (response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
            refreshTokenAndRetry();
        }
        return handleAnalyticsResponse(response.getBody());
    }

    public void refreshTokenAndRetry() {
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

    private Map<String, Object> handleAnalyticsResponse(String responseBody) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> responseMap = objectMapper.readValue(responseBody, Map.class);

            List<Map<String, Object>> rows = (List<Map<String, Object>>) responseMap.get("rows");
            if (rows != null && !rows.isEmpty()) {
                List<Map<String, String>> metricValues1 = (List<Map<String, String>>) rows.get(0).get("metricValues");
                List<Map<String, String>> metricValues2 = (List<Map<String, String>>) rows.get(1).get("metricValues");

                if (metricValues1 != null && !metricValues1.isEmpty()) {
                    // 첫 번째 메트릭 값의 'value' 추출
                    String visitorsCount1 = metricValues1.get(0).get("value");
                    System.out.println("방문자 수: " + visitorsCount1);
                    String visitorsCount2 = metricValues2.get(0).get("value");
                    System.out.println("방문자 수: " + visitorsCount2);
                }
            }
            return responseMap;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("응답 처리 중 오류 발생", e);
        }
    }
}

