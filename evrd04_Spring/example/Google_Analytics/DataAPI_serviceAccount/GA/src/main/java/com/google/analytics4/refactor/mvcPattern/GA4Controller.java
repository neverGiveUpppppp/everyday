package com.google.analytics4.refactor.mvcPattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GA4Controller {

    private final GA4Service service;

    @Autowired
    public GA4Controller(GA4Service service) {
        this.service = service;
    }


    @RequestMapping(value = "/hello")
    public String getAnalyticsData3(GoogleAnalytics4VO ga4Vo, Model model) {
        ga4Vo.setTodayVisitors(service.ga4CacheToday());
        ga4Vo.setAllVisitors(service.ga4CacheAll());
        model.addAttribute("ga4Vo",ga4Vo);
        return "/hello";
    }


    @RequestMapping(value = "/ga4")
    public String getAnalyticsDataAjax(GoogleAnalytics4VO ga4Vo, Model model) {
        ga4Vo.setTodayVisitors(service.ga4CacheToday());
        ga4Vo.setAllVisitors(service.ga4CacheAll());
        model.addAttribute("ga4Vo",ga4Vo);

        return "/helloAjax";
    }

    @RequestMapping(value = "/ga4Visitor")
    @ResponseBody
    public GoogleAnalytics4VO getAnalyticsDataAjaxCall(GoogleAnalytics4VO ga4Vo, Model model) {
        ga4Vo.setTodayVisitors(service.ga4CacheToday());
        ga4Vo.setAllVisitors(service.ga4CacheAll());
        return ga4Vo; // {"todayVisitors":"10","allVisitors":"4009"}
    }


}
