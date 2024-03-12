package google.oauth2.product;

public interface GoogleAnalyticsService {
    
    GoogleAnalysticsVO makeAnalyticsRequest(GoogleAnalysticsVO gaVo, String accessToken);
    void refreshTokenAndRetry(GoogleAnalysticsVO gaVo);
    
}