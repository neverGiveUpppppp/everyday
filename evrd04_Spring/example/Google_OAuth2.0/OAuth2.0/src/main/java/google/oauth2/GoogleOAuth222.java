//import org.springframework.beans.factory.annotation.Autowired; // 대응 없음
//import org.springframework.http.*; // 대응 없음
//import org.springframework.stereotype.Service; // 대응 없음
//import org.springframework.web.client.RestTemplate; // $.ajax
//import com.fasterxml.jackson.databind.ObjectMapper; // 대응 없음
//
//import java.util.HashMap; // 객체 리터럴 ({})
//import java.util.Map; // 객체 리터럴 ({})
//
//@Service // 대응 없음
//public class AnalyticsService {
//
//    @Autowired // 대응 없음
//    private RestTemplate restTemplate; // $.ajax
//
//    private static final String ANALYTICS_URL = "https://analyticsdata.googleapis.com/v1beta/properties/425548737:runReport"; // url: 'https://analyticsdata.googleapis.com/v1beta/properties/425548737:runReport',
//    private static final String REFRESH_TOKEN_URL = "https://oauth2.googleapis.com/token"; // url: 'https://oauth2.googleapis.com/token',
//    private static final String INITIAL_ACCESS_TOKEN = "..."; // 'Authorization': 'Bearer ya29.a0Ad5...'
//
//    // HttpHeaders랑 Map으로 body부분을 만들어서 HttpEntity객체에 넣고 request할 객체 생성
//    public void makeAnalyticsRequest(String accessToken) { // function makeAnalyticsRequest(accessToken) {
//        HttpHeaders headers = new HttpHeaders(); // var headers = { ... };
//        headers.setContentType(MediaType.APPLICATION_JSON); // 'Content-Type': 'application/json'
//        headers.setBearerAuth(accessToken); // 'Authorization': 'Bearer ' + accessToken,
//
//        Map<String, Object> requestBody = new HashMap<>(); // var data = JSON.stringify({...});
//        requestBody.put("dimensions", new Object[]{new HashMap<String, Object>() {{ // "dimensions": [{ "name": "country" }],
//            put("name", "country");
//        }}});
//        requestBody.put("metrics", new Object[]{new HashMap<String, Object>() {{    // "metrics": [{ "name": "activeUsers" }],
//            put("name", "activeUsers");
//        }}});
//        requestBody.put("dateRanges", new Object[]{new HashMap<String, Object>() {{ // "dateRanges": [{ "startDate": "2024-01-01", "endDate": "2024-03-05" }]
//            put("startDate", "2024-01-01");
//            put("endDate", "2024-03-05");
//        }}});
//
//        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers); // $.ajax({headers: headers, data: data, ...});
//
//        try {
//            ResponseEntity<String> response = restTemplate.postForEntity(ANALYTICS_URL, entity, String.class); // $.ajax({...})
//            System.out.println(response.getBody()); // success: function(response) { console.log(response); }
//        } catch (HttpClientErrorException e) {
//            if (e.getStatusCode() == HttpStatus.UNAUTHORIZED) { // if(xhr.status == 401) {
//                refreshTokenAndRetryRequest(); // refreshTokenAndRetryRequest();
//            } else {
//                throw e; // else { console.error('Request failed', e); }
//            }
//        }
//    }
//
//    public void refreshTokenAndRetryRequest() { // function refreshTokenAndRetryRequest() {
//        HttpHeaders headers = new HttpHeaders(); // var headers = { ... };
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED); // 'Content-Type': 'application/x-www-form-urlencoded',
//
//        MultiValueMap<String, String> map= new LinkedMultiValueMap<>(); // var data = { ... };
//        map.add("client_id", "클라이언트ID"); // 'client_id': '...'
//        map.add("client_secret", "클라이언트Secret"); // 'client_secret': '...'
//        map.add("grant_type", "refresh_token"); // 'grant_type': 'refresh_token',
//        map.add("refresh_token", "새로고침토큰"); // 'refresh_token': '...',
//
//        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers); // $.ajax({headers: headers, data: data, ...});
//
//        ResponseEntity<String> response = restTemplate.postForEntity(REFRESH_TOKEN_URL, request , String.class); // $.ajax({...})
//
//        try {
//            ObjectMapper objectMapper = new ObjectMapper(); // 대응 없음
//            Map<String, String> result = objectMapper.readValue(response.getBody(), Map.class); // success: function(response) {...}
//            String newAccessToken = result.get("access_token"); // var
