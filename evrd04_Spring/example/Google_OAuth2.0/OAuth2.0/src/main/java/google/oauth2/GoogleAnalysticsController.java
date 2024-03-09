package google.oauth2;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Controller
public class GoogleAnalysticsController {

    private final GoogleAnalyticsService googleAnalyticsService;

    public GoogleAnalysticsController(GoogleAnalyticsService googleAnalyticsService) {
        this.googleAnalyticsService = googleAnalyticsService;
    }

    @RequestMapping("/oauth2")
    public void makeAnalyticsRequest(@RequestBody String accessToken, Model model) {
        Map<String, Object> response = googleAnalyticsService.makeAnalyticsRequest(accessToken);
//        return new ResponseEntity<>(response, HttpStatus.OK);
        model.addAttribute(response);
//        return model;
    }

}
