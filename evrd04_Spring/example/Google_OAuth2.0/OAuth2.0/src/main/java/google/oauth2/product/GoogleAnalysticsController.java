package google.oauth2.product;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import zesinc.user.ga4OAuth2.domain.GoogleAnalysticsVO;
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
        String accessToken = "ya29.a0Ad52N39vbOwTiuw-T0F0Gz2-AcTSA99zq4anM5gsqnGqm42nOBjvP2Tkc0P1dGAASwgna4b5pv03o0ivW-rpzutwLD2H48sVlNHmz-9sEQNsVA1GozKHSD5RSM65zrIx5Wdnxe81WJdbI2YpurQsvc8TmLdKmMH9QNNFaCgYKAS0SARMSFQHGX2MiLbPojnus-WC7ojesmCPQ0A0171";
        GoogleAnalysticsVO response = gaService.getVisitorCache(gaVo, accessToken);
        model.addAttribute("ga4Vo", response);
        return responseJson(model, response);
    }

    
}
