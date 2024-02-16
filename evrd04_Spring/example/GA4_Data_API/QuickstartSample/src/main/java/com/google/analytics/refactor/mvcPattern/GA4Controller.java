package com.google.analytics.refactor.mvcPattern;

import com.google.analytics.data.v1beta.BetaAnalyticsDataClient;
import com.google.analytics.data.v1beta.RunReportRequest;
import com.google.analytics.data.v1beta.RunReportResponse;
import org.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

@Controller
public class GA4Controller {

//    private final GA4ServiceImpl service;
//
//    @Autowired
//    public GA4Controller(GA4ServiceImpl service) {
//        this.service = service;
//    }
    private final GA4ServiceCache service;

    @Autowired
    public GA4Controller(GA4ServiceCache service) {
        this.service = service;
    }

//    @RequestMapping(value = "/")
//    public String getAnalyticsData(GoogleAnalytics4VO ga4Vo, Model model) {
//        ga4Vo.setTodayVisitors(service.ga4CacheToday());
//        ga4Vo.setAllVisitors(service.ga4CacheAll());
//        model.addAttribute("ga4Vo",ga4Vo);
//        return "redirect:/home.html";
//    }

    // 인터셉터용
//    public String getAnalyticsData(GoogleAnalytics4VO ga4Vo, Model model) {
    @RequestMapping(value = "/helloMV")
    public void getAnalyticsData(GoogleAnalytics4VO ga4Vo, ModelAndView mv) {
        ga4Vo.setTodayVisitors(service.ga4CacheToday());
        ga4Vo.setAllVisitors(service.ga4CacheAll());
        mv.addObject("ga4Vo",ga4Vo);
        mv.setViewName("/hello");
//        return "/hello";
    }
    // 인터셉터용2
    public ModelAndView getAnalyticsData1(ModelAndView mv) {
        mv.addObject("today",service.ga4CacheToday());
        mv.addObject("all",service.ga4CacheAll());
        mv.setViewName("/hello");
        return mv;
    }
//    @RequestMapping(value = "/hello")
    public ModelAndView getAnalyticsData2(GoogleAnalytics4VO ga4Vo, ModelAndView mv) {
        ga4Vo.setTodayVisitors(service.ga4CacheToday());
        ga4Vo.setAllVisitors(service.ga4CacheAll());
        mv.addObject("ga4Vo",ga4Vo);
        mv.setViewName("/hello");
        return mv;
    }
    @RequestMapping(value = "/hello3")
    public String getAnalyticsData3(GoogleAnalytics4VO ga4Vo, Model model) {
        ga4Vo.setTodayVisitors(service.ga4CacheToday());
        ga4Vo.setAllVisitors(service.ga4CacheAll());
        model.addAttribute("ga4Vo",ga4Vo);
        return "/hello";
    }
    @RequestMapping(value = "/hello4")
    public ModelAndView getAnalyticsData4(GoogleAnalytics4VO ga4Vo, ModelAndView mv) {
        ga4Vo.setTodayVisitors(service.ga4CacheToday());
        ga4Vo.setAllVisitors(service.ga4CacheAll());
        mv.addObject("ga4Vo",ga4Vo);
        mv.setViewName("/hello");         // 에러발생 : 브라우저 Whitelabel Error Page
//        mv.setViewName("/hello.html");    // 에러발생 : TemplateInputException
//        mv.setViewName("/templates/hello");      // 에러발생 : 브라우저 Whitelabel Error Page
//        mv.setViewName("/templates/hello.html"); // 에러발생 : TemplateInputException
        return mv;
    }


/*************** HttpServlet의 application Scope에 저장하는 방법 사용 ***************/
// HttpServlet 및 HttpServletRequest가 interface라 호출 시, DI때문에 인스턴스 생성이 불가해서 막힌 상태 
    public String getTodayCnt(HttpServletRequest request, Model model){
        ServletContext servletContext = request.getServletContext(); // 서블릿 3.0이상에서 HttpServletRequest에서 getServletContext() 바로 가능
//        ServletContext servletContext = this.getServletContext();    // 서블릿 3.0이하에서는  extends HttpServlet 후 this.getServletContext()로 가능
        String todayCnt = String.valueOf(servletContext.getAttribute("todayCnt"));
        if (todayCnt == null) {
            todayCnt = service.ga4CacheToday();
        }
        return todayCnt;
    }

    public String getAllCnt(HttpServletRequest request, Model model){
        ServletContext servletContext = request.getServletContext();
        String allCnt = String.valueOf(servletContext.getAttribute("allCnt"));
        if (allCnt == null) {
            allCnt = service.ga4CacheAll();
        }
        return allCnt;
    }

}
