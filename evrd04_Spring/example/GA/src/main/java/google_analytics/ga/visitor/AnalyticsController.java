package testing;

import com.google.api.services.analyticsreporting.v4.model.GetReportsResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController {
    private AnalyticsDataFetcher analyticsDataFetcher;

    public AnalyticsController(AnalyticsDataFetcher analyticsDataFetcher) {
        this.analyticsDataFetcher = analyticsDataFetcher;
    }

    @GetMapping("/visitorData")
    public String getVisitorData() {
        try {
            GetReportsResponse response = analyticsDataFetcher.getVisitorData("YOUR_VIEW_ID");
            // 여기에서 응답 데이터를 원하는 형식으로 변환하여 반환
            return response.toString(); // 임시 코드, 실제 응답 처리 필요
        } catch (IOException e) {
            e.printStackTrace();
            return "Error fetching visitor data";
        }
    }
}
