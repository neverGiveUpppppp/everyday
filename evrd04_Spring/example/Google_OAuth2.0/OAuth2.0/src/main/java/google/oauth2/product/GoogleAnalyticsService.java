package google.oauth2.product;

import zesinc.user.ga4OAuth2.domain.GoogleAnalysticsVO;

public interface GoogleAnalyticsService {
    
    GoogleAnalysticsVO getVisitorCache(GoogleAnalysticsVO gaVo, String accessToken);
    void refreshTokenAndRetry(GoogleAnalysticsVO gaVo, String startDate, String endDate, int retryCnt);
    
}