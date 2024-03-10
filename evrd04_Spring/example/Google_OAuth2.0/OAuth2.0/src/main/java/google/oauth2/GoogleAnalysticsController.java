package google.oauth2;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequestMapping("/oauth2")
public class GoogleAnalysticsController {

    private final GoogleAnalyticsServiceImpl googleAnalyticsService;

    public GoogleAnalysticsController(GoogleAnalyticsServiceImpl googleAnalyticsService) {
        this.googleAnalyticsService = googleAnalyticsService;
    }

//    @RequestMapping(value = "/oauth2", method = RequestMethod.POST)
    @RequestMapping("/ga4")
//    public void makeAnalyticsRequest(@RequestBody String accessToken, Model model) {
//    public void makeAnalyticsRequest(@RequestBody String accessToken, Model model) {
//    public void makeAnalyticsRequest(@RequestParam("accessToken") String accessToken, Model model) {
    public void makeAnalyticsRequest(Model model) {
        String accessToken = "ya29.a0N3_BWFO5Fw-DWVl8Erqt_iqBLAsbZ_oISr_ym-1Y1II-0klxOYTZHOfmYWKEHKa";
        Map<String, Object> response = googleAnalyticsService.makeAnalyticsRequest(accessToken);
//        return new ResponseEntity<>(response, HttpStatus.OK);
        model.addAttribute("visitors", response);
//        return model;
    }

}
