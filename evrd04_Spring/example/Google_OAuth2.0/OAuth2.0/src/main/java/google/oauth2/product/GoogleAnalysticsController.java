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
        String accessToken = "ya29.a0Ad52N39YHt657QMALe6kMJMB4nV701coiiMUaAc-ZFse7Wa_EPnVLCSOSgxssDKEivWt3K4JD7qzxP_SUKJ5bxDrCJ2nQsMd4i_1eyiygoYtPvunqAePuJiDsB7tp7tcFyfSI4dkjPYY4DDRHLTFX6S3CtXFwpG8wcvraCgYKAWoSARMSFQHGX2MiJ93Moca_l__-AiKrJMx64A0171";
        GoogleAnalysticsVO response = gaService.makeAnalyticsRequest(gaVo, accessToken);
        model.addAttribute("ga4Vo", response);
        return responseJson(model, response);
    }

    
}
