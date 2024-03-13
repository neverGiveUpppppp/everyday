package google.oauth2.product;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import zesinc.web.spring.controller.UserController;

@Controller("GoogleAnalystics")
@RequestMapping("/user/ga4OAuth2")
public class GoogleAnalysticsController extends UserController{

    private final GoogleAnalyticsService gaService;

    @Autowired
    public GoogleAnalysticsController(GoogleAnalyticsService googleAnalyticsService) {
        this.gaService = googleAnalyticsService;
    }
    
    
    @RequestMapping("/ND_ga4Visitor.do")
    public String makeAnalyticsRequest(GoogleAnalysticsVO gaVo, Model model) {
//        String accessToken = "ya29.a0Ad52N3-mR0cY_jbeWj0EoqmKghF0kFSW91Yr7S6OLaKj_PTdD9sxHnhBlcg27Xzi9b6J8SbMr_Qy3u7ewc8WtwGhjnQBCvIyRsTewzlNpM3EAfmTnknfnr3i9-KPhpkw_LKpv-9NQFFPxVuK3vDIjujkcvoXW_ghb0XaaCgYKASQSARESFQHGX2Mi1_6XzHmAWle2xrURYKthPw0171";
//        GoogleAnalysticsVO response = gaService.makeAnalyticsRequest(gaVo, accessToken);
        String accessToken = "ya29.a0Ad52N392NKPoZCKNWYI-ZfSRBHiVCo9EdkNbS4vWq3M6rPOpLruvnLXfIx-WLAg1ndh9dkK9WhHYVor-HOXmHHekroMVNVhLmpwJL9-zmVXNrODjlWhqfnpXi9Ysx60PKdoHjE3SKHFdkiV6-E8cYf6r1uMcZDGmtQ4VaCgYKAYwSARESFQHGX2Mici8kM_wzPTkLBNs0988SOA0171";
        GoogleAnalysticsVO response = gaService.getVisitorCache(gaVo, accessToken);
        model.addAttribute("ga4Vo", response);
        return responseJson(model, response);
    }

    
}
