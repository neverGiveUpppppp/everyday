package google.oauth2.product;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import zesinc.web.spring.controller.UserController;

import java.util.Map;

@Controller("GoogleAnalystics")
@RequestMapping("/user/ga4OAuth2")
public class GoogleAnalysticsController extends UserController{

//    private final GoogleAnalyticsService gaService;
//
//    public GoogleAnalysticsController(GoogleAnalyticsService googleAnalyticsService) {
//        this.gaService = googleAnalyticsService;
//    }
    private final GoogleAnalyticsService gaService;

    @Autowired
    public GoogleAnalysticsController(GoogleAnalyticsService googleAnalyticsService) {
        this.gaService = googleAnalyticsService;
    }

    @RequestMapping("/ND_ga4Visitor.do")
    public String makeAnalyticsRequest(GoogleAnalysticsVO gaVo, Model model) {
//    public ResponseEntity<Map<String, Object>> makeAnalyticsRequest(Model model) {
        String accessToken = "ya29.a0Ad52N3_7GHJ3a2fnOKDJYeNYIMBHX6K3pkkGxwPaDKnpis5uzy02HHOkA_t6YaDR9076evwlU5_O1O_47el828dgtQ29BrK2jPI695EmpT5d3FZXpJLOfC1wve4qhHbWYp6J0FUPZ3dJaW-bzIIlpsuUROD8LzaoUaZfaCgYKAY4SARMSFQHGX2Mi3t9QF2aGuXXJ8GMoKb5pNw0171";
        GoogleAnalysticsVO response = gaService.makeAnalyticsRequest(gaVo, accessToken);
        model.addAttribute("ga4Vo", response);
        return responseJson(model, response);
    }

    
}
