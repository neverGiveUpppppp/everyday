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
        String accessToken = "ya29.a0Ad52N38LHaqvEaX-VtqSe697p10NRYNhE2RsutZZa-TpyOY7jEeLLU8-S1mkKZJmuU4ysasdoMo6Fi0uBCq7FYzblEmCI84NsSM-6ZlQVi921eeCpaeFGc-CsO1t-mYya1jxuKFJff5MQj6pE6Ns5RiekNshPEP286_laCgYKATMSARMSFQHGX2MiYN40vNekEikpmSUTT5_eAQ0171";
        GoogleAnalysticsVO response = gaService.getVisitorCache(gaVo, accessToken);
        model.addAttribute("ga4Vo", response);
        return responseJson(model, response);
    }

    
}
