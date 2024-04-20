package google.oauth2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/oauth2")
public class GoogleAnalysticsController {

    private final GoogleAnalyticsService gaService;

    public GoogleAnalysticsController(GoogleAnalyticsService googleAnalyticsService) {
        this.gaService = googleAnalyticsService;
    }

//    @RequestMapping(value = "/oauth2", method = RequestMethod.POST)

    @RequestMapping("/ga4")
    @ResponseBody
//    public void makeAnalyticsRequest(@RequestBody String accessToken, Model model) {
//    public void makeAnalyticsRequest(@RequestBody String accessToken, Model model) {
//    public void makeAnalyticsRequest(@RequestParam("accessToken") String accessToken, Model model) {
    public Map<String, Object> makeAnalyticsRequest(Model model) {
//    public ResponseEntity<Map<String, Object>> makeAnalyticsRequest(Model model) {
        String accessToken = "ya29.a0Ad52N398WVP7iRKeS_COxR8k_JBnkG6TvU9YYBlNH9eLSGAsAT0A_XmdIgtsEimdjTEinccZV7nvylPXUp9Und-GhfsCjmfVZzoD7r7thvYaQlPqyyC5-vxRFJ3-LNNN_Tip2Q3hs56zGObkvePFdLoUYDEvDnyeR2iXaCgYKAdoSARMSFQHGX2Mi7pS5921pEk0s6Dn5mBwjIg0171";
        Map<String, Object> response = gaService.makeAnalyticsRequest(accessToken);
//        model.addAttribute("visitors", response);
        return response;
//        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
