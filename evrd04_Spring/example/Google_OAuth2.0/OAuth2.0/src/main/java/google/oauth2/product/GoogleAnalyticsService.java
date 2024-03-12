package google.oauth2.product;

import java.util.Map;


public interface GoogleAnalyticsService {
    GoogleAnalysticsVO makeAnalyticsRequest(GoogleAnalysticsVO gaVo, String accessToken);

}