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
        String accessToken = "ya29.a0Ad52N38vm0h0Bhdks54oiJxgf0OKOjzwHeQMC2Cv2_RXj2BqQM253ELC6p2HkwOb64sEjRT58-wSNXlG8IIpjqJE8vNnzQcPiEgPZBr2HlA_M0HhjQiUv2Wj9sozwZDZZv78zBA1E6sxkGG2ONCz00hLcZ8xa-ZZs6ucaCgYKASoSARMSFQHGX2MiF7E5nJS6dR9tGx-Uj039bg0171";
        Map<String, Object> response = gaService.makeAnalyticsRequest(accessToken);
//        model.addAttribute("visitors", response);
        return response;
//        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
