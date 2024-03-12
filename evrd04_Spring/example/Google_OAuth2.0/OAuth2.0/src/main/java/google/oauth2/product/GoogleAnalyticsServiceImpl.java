package google.oauth2.product;


import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;





@Service
public class GoogleAnalyticsServiceImpl implements GoogleAnalyticsService{

    
    private final RestTemplate restTemplate;
    
    @Autowired
    public GoogleAnalyticsServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
      
    public GoogleAnalyticsServiceImpl() {
        this.restTemplate = new RestTemplate();
    }

    public GoogleAnalysticsVO makeAnalyticsRequest(GoogleAnalysticsVO gaVo, String accessToken) throws HttpClientErrorException{

        HttpHeaders httpHeaders1 = makeHeaders(accessToken);
        HttpEntity<Map<String, Object>> httpEntity1 = makeBodyToday(httpHeaders1);

        ResponseEntity<String> response1 = restTemplate.postForEntity(GoogleOAuth2.ANALYTICS_URL, httpEntity1, String.class);
        if (response1.getStatusCode() == HttpStatus.UNAUTHORIZED) { // 액세스 토큰이 만료된 경우
            refreshTokenAndRetry(gaVo); // 토큰 새로고침 후 요청 재시도
        }
        String visitorsToday = handleAnalyticsResponse(response1.getBody());
        gaVo.setToday(visitorsToday);
        
        HttpHeaders httpHeaders2 = makeHeaders(accessToken);
        HttpEntity<Map<String, Object>> httpEntity2 = makeBodyAll(httpHeaders2);

        ResponseEntity<String> response2 = restTemplate.postForEntity(GoogleOAuth2.ANALYTICS_URL, httpEntity2, String.class);
        if (response2.getStatusCode() == HttpStatus.UNAUTHORIZED) { // 액세스 토큰이 만료된 경우
            refreshTokenAndRetry(gaVo); // 토큰 새로고침 후 요청 재시도
        }
        String visitorsAll = handleAnalyticsResponse(response2.getBody());
        gaVo.setAll(visitorsAll);
        return gaVo;
    }

    public HttpHeaders makeHeaders(String accessToken){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + accessToken); 
        return headers;
    }

    public HttpEntity<Map<String, Object>> makeBodyToday(HttpHeaders httpHeaders){
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("dimensions", new Object[]{new HashMap<String, Object>() {{
            put("name", "country");
        }}});
        requestBody.put("metrics", new Object[]{new HashMap<String, Object>() {{
            put("name", "activeUsers");
        }}});
        requestBody.put("dateRanges", new Object[]{new HashMap<String, Object>() {{
            put("startDate", "today");
            put("endDate", "today");
        }}});
        return new HttpEntity<>(requestBody, httpHeaders);
    }
    
    public HttpEntity<Map<String, Object>> makeBodyAll(HttpHeaders httpHeaders){
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
        return new HttpEntity<>(requestBody, httpHeaders);
    }

    public void refreshTokenAndRetry(GoogleAnalysticsVO gaVo) {
        HttpHeaders headers = new HttpHeaders(); // HTTP 헤더 생성
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED); // 내용 타입을 Form-Urlencoded로 설정

        MultiValueMap<String, String> map= new LinkedMultiValueMap<>(); // 토큰 새로고침 요청 본문 데이터
        // OAuth2 클라이언트 정보와 새로고침 토큰 설정
        map.add("client_id", GoogleOAuth2.CLIENT_ID);
        map.add("client_secret", GoogleOAuth2.CLIENT_SECRET);
        map.add("refresh_token", GoogleOAuth2.REFRESH_TOKEN);
        map.add("grant_type", "refresh_token");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers); // 요청 엔티티 생성

        ResponseEntity<String> response = restTemplate.postForEntity(GoogleOAuth2.REFRESH_TOKEN_URL, request , String.class); // 토큰 새로고침 요청 실행

        try {
            Gson gson = new Gson(); // Gson 인스턴스 생성
            Type type = new TypeToken<Map<String, String>>(){}.getType(); // 문자열을 Map으로 변환하기 위한 타입
            Map<String, String> result = gson.fromJson(response.getBody(), type); // 응답 본문을 Map으로 변환
            String newAccessToken = result.get("access_token"); // 새 액세스 토큰 추출
            makeAnalyticsRequest(gaVo,newAccessToken); // 새 토큰으로 요청 재시도
        } catch (Exception e) {
            e.printStackTrace(); // 예외 정보 출력
            throw new RuntimeException("Refresh token 요청 실패", e); // 예외 던짐
        }
//        try {
//            
////            ObjectMapper objectMapper = new ObjectMapper();     // JSON 문자열을 객체로 변환하기 위한 ObjectMapper
////            Map<String, String> result = objectMapper.readValue(response.getBody(), Map.class); // 응답 본문을 Map으로 변환
////            String newAccessToken = result.get("access_token"); // 새 액세스 토큰 추출
////            makeAnalyticsRequest(newAccessToken);               // 새 토큰으로 요청 재시도
//        } catch (Exception e) {
//            e.printStackTrace();    // 예외 정보 출력
//            throw new RuntimeException("Refresh token 요청 실패", e); // 예외 던짐
//        }
    }
    
    
    // jackson lib(objectMapper)을 읽지못하여   org.json(JSONObject)으로 변경 시도
    private String handleAnalyticsResponse(String responseBody) {
        try {
            
            // Gson 사용 : return responseMap
            Gson gson = new Gson();
            Type type = new TypeToken<Map<String, Object>>(){}.getType();
            Map<String, Object> responseMap = gson.fromJson(responseBody, type);
                
            String visitorsCount = null;
            DecimalFormat formatter = new DecimalFormat();
            List<Map<String, Object>> rows = (List<Map<String, Object>>) responseMap.get("rows");
            if (rows != null && !rows.isEmpty()) {
                List<Map<String, String>> metricValues = (List<Map<String, String>>) rows.get(0).get("metricValues");

                if (metricValues != null && !metricValues.isEmpty()) {
                    visitorsCount = formatter.format(Long.parseLong(metricValues.get(0).get("value")));
                }else {
                    visitorsCount = "0";
                }
                
            }else {
                visitorsCount = "0";
            }
            return visitorsCount;
            
            
//            // Gson 사용 : return responseMap
//            Gson gson = new Gson();
//            Type type = new TypeToken<Map<String, Object>>(){}.getType();
//            Map<String, Object> responseMap = gson.fromJson(responseBody, type);
//
//            List<Map<String, Object>> rows = (List<Map<String, Object>>) responseMap.get("rows");
//            if (rows != null && !rows.isEmpty()) {
//                List<Map<String, String>> metricValues1 = (List<Map<String, String>>) rows.get(0).get("metricValues");
//                List<Map<String, String>> metricValues2 = (List<Map<String, String>>) rows.get(1).get("metricValues");
//
//                if (metricValues1 != null && !metricValues1.isEmpty()) {
//                    String visitorsCount1 = metricValues1.get(0).get("value");
//                    System.out.println("방문자 수: " + visitorsCount1);
//                    String visitorsCount2 = metricValues2.get(0).get("value");
//                    System.out.println("방문자 수: " + visitorsCount2);
//                }
//            }
//            return responseMap;
            
            //  org.json(JSONObject) : responseObj.toMap() 에러
//            JSONObject responseObj = new JSONObject(responseBody);
//            JSONArray rows = responseObj.getJSONArray("rows");
//            
//            if (rows != null && rows.length() > 0) {
//                JSONArray metricValues1 = rows.getJSONObject(0).getJSONArray("metricValues");
//                JSONArray metricValues2 = rows.getJSONObject(1).getJSONArray("metricValues");
//
//                if (metricValues1 != null && metricValues1.length() > 0) {
//                    String visitorsCount1 = metricValues1.getJSONObject(0).getString("value");
//                    System.out.println("방문자 수: " + visitorsCount1);
//                    String visitorsCount2 = metricValues2.getJSONObject(0).getString("value");
//                    System.out.println("방문자 수: " + visitorsCount2);
//                }
//            }
//            // JSONObject를 Map<String, Object>로 변환
//            return responseObj.toMap();
            
            
            // jackson lib(objectMapper)
//            Map<String, Object> responseMap = objectMapper.readValue(responseBody, Map.class);
//    
//            List<Map<String, Object>> rows = (List<Map<String, Object>>) responseMap.get("rows");
//            if (rows != null && !rows.isEmpty()) {
//                List<Map<String, String>> metricValues1 = (List<Map<String, String>>) rows.get(0).get("metricValues");
//                List<Map<String, String>> metricValues2 = (List<Map<String, String>>) rows.get(1).get("metricValues");
//    
//                if (metricValues1 != null && !metricValues1.isEmpty()) {
//                    // 첫 번째 메트릭 값의 'value' 추출
//                    String visitorsCount1 = metricValues1.get(0).get("value");
//                    System.out.println("방문자 수: " + visitorsCount1);
//                    String visitorsCount2 = metricValues2.get(0).get("value");
//                    System.out.println("방문자 수: " + visitorsCount2);
//                }
//            }
//            return responseMap;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("응답 처리 중 오류 발생", e);
        }
    }
    
}