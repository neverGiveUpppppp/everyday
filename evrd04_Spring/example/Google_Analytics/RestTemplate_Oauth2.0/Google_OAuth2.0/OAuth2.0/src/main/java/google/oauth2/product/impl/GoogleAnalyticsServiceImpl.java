package google.oauth2.product.impl;

import zesinc.user.ga4OAuth2.GoogleAnalyticsService;
import zesinc.user.ga4OAuth2.GoogleOAuth2;
import zesinc.user.ga4OAuth2.domain.GoogleAnalysticsVO;
import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private final Gson gson;
    private final CacheManager cacheManager;
    
    final Logger logger = LoggerFactory.getLogger(getClass());
    private static final Type RESPONSE_TYPE = new TypeToken<Map<String, Object>>(){}.getType();
    
    @Autowired
    public GoogleAnalyticsServiceImpl(RestTemplate restTemplate, Gson gson, CacheManager cacheManager) {
        this.restTemplate = restTemplate;
        this.gson = gson;
        this.cacheManager = cacheManager; 
    }
    
    
    public GoogleAnalysticsVO getVisitorCache(GoogleAnalysticsVO gaVo, String accessToken) {
        Cache<String, String> cache = cacheManager.getCache("visitorCounts", String.class, String.class);
        
        // 오늘 방문자 수 조회
        String todayNum = "todayNum";          
        String todayCnt = cache.get(todayNum); 
        if (todayCnt == null) {
            String today = callApi(gaVo, accessToken, "today", "today", 5); // GA4에서 방문자 수 가져오기
            // 캐시에 저장
            cache.put(todayNum, today);
            todayCnt = cache.get(todayNum);
            logger.info(todayCnt);
        }
        gaVo.setToday(todayCnt);
        
     // 전체 방문자 수 조회
        String allNum = "allNum";          
        String allCnt = cache.get(allNum); 
    
        if (allCnt == null) {
          String all = callApi(gaVo, accessToken, "2024-01-28", "today", 5); // GA4에서 방문자 수 가져오기
            // 캐시에 저장
            cache.put(allNum, all);
            allCnt = cache.get(allNum);
            logger.info(allCnt);
        }
        gaVo.setAll(allCnt);
        return gaVo;
    }
    
    public String callApi(GoogleAnalysticsVO gaVo, String accessToken, String startDate, String endDate, int retryCnt) {
        HttpHeaders httpHeaders = makeHeaders(accessToken);
        HttpEntity<Map<String, Object>> httpEntity = makeBody(httpHeaders, startDate, endDate);
        ResponseEntity<String> response = restTemplate.postForEntity(GoogleOAuth2.ANALYTICS_URL, httpEntity, String.class);
        logger.debug("Http response reuslt : " + response);
        if (response.getStatusCode().equals(HttpStatus.UNAUTHORIZED)) {
            logger.info("Unauthorized request. Refreshing token and retrying : " + response.getStatusCode() );
            if (retryCnt <= 0) {
                throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "인증 실패: 재시도 횟수 초과");
            }
            refreshTokenAndRetry(gaVo, startDate, endDate, retryCnt - 1); // 토큰 새로고침 후 요청 재시도
        }
        return handleAnalyticsResponse(response.getBody());
    }

    public HttpHeaders makeHeaders(String accessToken){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.add("Authorization", "Bearer " + accessToken); 
        return headers;
    }

    public HttpEntity<Map<String, Object>> makeBody(HttpHeaders httpHeaders, String startDate, String endDate){
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("dimensions", new Object[]{new HashMap<String, Object>() {{
            put("name", "country");
        }}});
        requestBody.put("metrics", new Object[]{new HashMap<String, Object>() {{
            put("name", "activeUsers");
        }}});
        requestBody.put("dateRanges", new Object[]{new HashMap<String, Object>() {{
            put("startDate", startDate);
            put("endDate", endDate);
        }}});
        return new HttpEntity<>(requestBody, httpHeaders);
    }
    
    public void refreshTokenAndRetry(GoogleAnalysticsVO gaVo, String startDate, String endDate, int retryCnt) {
        HttpHeaders headers = new HttpHeaders(); // HTTP 헤더 생성
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED); // 내용 타입을 Form-Urlencoded로 설정
        
        // OAuth2 클라이언트 정보와 새로고침 토큰 설정
        MultiValueMap<String, String> map= new LinkedMultiValueMap<>(); // 토큰 새로고침 요청 본문 데이터
        map.add("client_id", GoogleOAuth2.CLIENT_ID);
        map.add("client_secret", GoogleOAuth2.CLIENT_SECRET);
        map.add("refresh_token", GoogleOAuth2.REFRESH_TOKEN);
        map.add("grant_type", "refresh_token");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers); // 요청 엔티티 생성
        ResponseEntity<String> response = restTemplate.postForEntity(GoogleOAuth2.REFRESH_TOKEN_URL, request , String.class); // 토큰 새로고침 요청 실행

        try {
            Map<String, String> result = gson.fromJson(response.getBody(), RESPONSE_TYPE); // 응답 본문을 Map으로 변환
            String newAccessToken = result.get("access_token");          // 새 액세스 토큰 추출
            callApi(gaVo, newAccessToken, startDate, endDate, retryCnt); // 새 토큰으로 요청 재시도
        } catch (Exception e) {
            e.printStackTrace(); // 예외 정보 출력
            throw new RuntimeException("Refresh token 요청 실패", e);
        }
    }
    
    private String handleAnalyticsResponse(String responseBody) {
        try {
            Map<String, Object> responseMap = gson.fromJson(responseBody, RESPONSE_TYPE);
                
            String visitorsCount = null;
            DecimalFormat formatter = new DecimalFormat();
            List<Map<String, Object>> rows = (List<Map<String, Object>>) responseMap.get("rows");
            if (rows != null && !rows.isEmpty()) {
                int totalVisitors = 0;
                for (Map<String, Object> row : rows) {
                
                    List<Map<String, String>> metricValues = (List<Map<String, String>>) row.get("metricValues");
                    if (metricValues != null && !metricValues.isEmpty()) {
                        totalVisitors += Integer.parseInt(metricValues.get(0).get("value"));
                    }else {
                        visitorsCount = "0";
                    }
                }
                visitorsCount = formatter.format(totalVisitors);
            }else {
                visitorsCount = "0";
            }
            return visitorsCount;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("응답 처리 중 오류 발생", e);
        }
    }



}