package com.google.analytics.GA4OnAir;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import zesinc.user.ga4.domain.GoogleAnalytics4VO;
import zesinc.web.spring.controller.UserController;

@Controller
@RequestMapping(value = { "/user/ga4" })
public class GoogleAnalyticsDataController extends UserController {

  private final GoogleAnalyticsDataService service;

  @Autowired
  public GoogleAnalyticsDataController(GoogleAnalyticsDataService service) {
      this.service = service;
  }
    
      @RequestMapping(value = "/ND_ga4Visitor.do")
      public String ga4DataAjaxCall(GoogleAnalytics4VO ga4Vo, Model model) {
          ga4Vo.setTodayVisitors(service.ga4CacheToday());
          ga4Vo.setAllVisitors(service.ga4CacheAll());  
          return responseJson(model, ga4Vo); // BaseController의 responseJson()에서 model.addAttribute() 해줌
      }

      
}
