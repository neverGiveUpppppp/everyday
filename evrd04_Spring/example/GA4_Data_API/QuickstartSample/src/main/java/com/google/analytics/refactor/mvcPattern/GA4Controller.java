package com.google.analytics.refactor.mvcPattern;

import com.google.analytics.data.v1beta.BetaAnalyticsDataClient;
import com.google.analytics.data.v1beta.RunReportRequest;
import com.google.analytics.data.v1beta.RunReportResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class GA4Controller {

    private final GA4ServiceImpl service;

    @Autowired
    public GA4Controller(GA4ServiceImpl service) {
        this.service = service;
    }

    public GoogleAnalytics4VO getAnalyticsData() {
        GoogleAnalytics4VO ga4Vo = service.ga4VisitorNum();
        System.out.println(ga4Vo.getTodayVisitors());
        System.out.println(ga4Vo.getAllVisitors());
        return ga4Vo;
    }

}