package google.oauth2;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

public class GA4VisitorsResponseHandler {

    // response.getBody()의 파싱용 메소드
    public void handleAnalyticsResponse(String responseBody) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> responseMap = objectMapper.readValue(responseBody, Map.class);

            // 예시 응답 구조에 따라 경로를 조정해야 할 수 있습니다.
            List<Map<String, Object>> rows = (List<Map<String, Object>>) responseMap.get("rows");
            if (rows != null && !rows.isEmpty()) {
                // 첫 번째 'row'의 'metricValues' 가져오기
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
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("응답 처리 중 오류 발생", e);
        }
    }
}
