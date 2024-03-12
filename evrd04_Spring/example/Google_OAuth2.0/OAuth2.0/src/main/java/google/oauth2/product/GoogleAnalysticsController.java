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
    
    
    @RequestMapping("/ND_ga4Visitors.do")
    public String makeAnalyticsRequest(GoogleAnalysticsVO gaVo, Model model) {
        String accessToken = "ya29.a0Ad52N39lV2SgQ8-Tivm02slIq3h1wEXdees4jfysq5gDCUTcww6XMb40x7UGiSi562nM3gKbfyXNG4-PvdJlo5r8kqeubvFVnVMuXNPa5FlDjklOUt2AN1BFTU3rqJStxrZoUDoQEuuRYvJWKhQDTRqySFgDEnEvjgtgaCgYKAe4SARMSFQHGX2MioO77y1myvhQPA5MnIeeTDw0171";
        GoogleAnalysticsVO response = gaService.makeAnalyticsRequest(gaVo, accessToken);
        model.addAttribute("ga4Vo", response);
        return responseJson(model, response);
    }

    
}
