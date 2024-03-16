//package com.google.analytics.refactor.mvcPattern;
//
//import org.springframework.stereotype.Component;
//import org.springframework.ui.Model;
//import org.springframework.web.servlet.HandlerInterceptor;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//@Component
//public class GA4Interceptor implements HandlerInterceptor {
//
//    private final GA4Controller controller;
//
//    public GA4Interceptor(GA4Controller controller) {
//        this.controller = controller;
//    }
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//
////        GoogleAnalytics4VO ga4Vo = new GoogleAnalytics4VO();
//        ModelAndView mv = new ModelAndView();
////        controller.getAnalyticsData(ga4Vo, mv);
//        controller.getAnalyticsData1(mv);
////        controller.getAnalyticsData2(ga4Vo, mv);
//        return true;
//    }
//
//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//
//
//    }
//}
