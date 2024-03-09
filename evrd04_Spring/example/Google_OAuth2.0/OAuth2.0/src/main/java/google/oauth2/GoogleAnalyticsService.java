package google.oauth2;

import java.util.Map;

public interface GoogleAnalyticsService {
    Map<String, Object> makeAnalyticsRequest(String accessToken);
    void refreshTokenAndRetry();

}
