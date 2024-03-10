package google.oauth2;

import java.util.Map;

public interface GoogleAnalyticsService {
    void makeAnalyticsRequest(String accessToken);
    void refreshTokenAndRetry();

}
