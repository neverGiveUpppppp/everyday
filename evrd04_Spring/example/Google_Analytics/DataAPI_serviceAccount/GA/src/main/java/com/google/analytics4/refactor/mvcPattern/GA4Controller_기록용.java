package com.google.analytics4.refactor.mvcPattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class GA4Controller_기록용 {

//    private final GA4ServiceImpl service;
//
//    @Autowired
//    public GA4Controller(GA4ServiceImpl service) {
//        this.service = service;
//    }
    private final GA4ServiceImpl service;

    @Autowired
    public GA4Controller_기록용(GA4ServiceImpl service) {
        this.service = service;
    }

//    @RequestMapping(value = "/")
//    public String getAnalyticsData(GoogleAnalytics4VO ga4Vo, Model model) {
//        ga4Vo.setTodayVisitors(service.ga4CacheToday());
//        ga4Vo.setAllVisitors(service.ga4CacheAll());
//        model.addAttribute("ga4Vo",ga4Vo);
//        return "redirect:/home.html";
//    }

//    // 인터셉터용
////    public String getAnalyticsData(GoogleAnalytics4VO ga4Vo, Model model) {
//    @RequestMapping(value = "/helloMV")
//    public void getAnalyticsData(GoogleAnalytics4VO ga4Vo, ModelAndView mv) {
//        ga4Vo.setTodayVisitors(service.ga4CacheToday());
//        ga4Vo.setAllVisitors(service.ga4CacheAll());
//        mv.addObject("ga4Vo",ga4Vo);
//        mv.setViewName("/hello");
////        return "/hello";
//    }
//    // 인터셉터용2
//    public ModelAndView getAnalyticsData1(ModelAndView mv) {
//        mv.addObject("today",service.ga4CacheToday());
//        mv.addObject("all",service.ga4CacheAll());
//        mv.setViewName("/hello");
//        return mv;
//    }
////    @RequestMapping(value = "/hello")
//    public ModelAndView getAnalyticsData2(GoogleAnalytics4VO ga4Vo, ModelAndView mv) {
//        ga4Vo.setTodayVisitors(service.ga4CacheToday());
//        ga4Vo.setAllVisitors(service.ga4CacheAll());
//        mv.addObject("ga4Vo",ga4Vo);
//        mv.setViewName("/hello");
//        return mv;
//    }


    // /hello 정상작동 테스트용
    @RequestMapping(value = "/hello")
    public String getAnalyticsData3(GoogleAnalytics4VO ga4Vo, Model model) {
        ga4Vo.setTodayVisitors(service.ga4CacheToday());
        ga4Vo.setAllVisitors(service.ga4CacheAll());
        model.addAttribute("ga4Vo",ga4Vo);
        return "/hello";
    }



//    @RequestMapping(value = "/hello4")
//    public ModelAndView getAnalyticsData4(GoogleAnalytics4VO ga4Vo, ModelAndView mv) {
//        ga4Vo.setTodayVisitors(service.ga4CacheToday());
//        ga4Vo.setAllVisitors(service.ga4CacheAll());
//        mv.addObject("ga4Vo",ga4Vo);
//        mv.setViewName("/hello");         // 에러발생 : 브라우저 Whitelabel Error Page
////        mv.setViewName("/hello.html");    // 에러발생 : TemplateInputException
////        mv.setViewName("/templates/hello");      // 에러발생 : 브라우저 Whitelabel Error Page
////        mv.setViewName("/templates/hello.html"); // 에러발생 : TemplateInputException
//        return mv;
//    }
//    @RequestMapping(value = "/hello5")
//    public ModelAndView getAnalyticsData5(GoogleAnalytics4VO ga4Vo, ModelAndView mv) {
//        ga4Vo.setTodayVisitors(service.ga4CacheToday());
//        ga4Vo.setAllVisitors(service.ga4CacheAll());
//        mv.addObject("ga4Vo",ga4Vo);
//        mv.setViewName("/menu/hello");         // 에러발생 : 브라우저 Whitelabel Error Page
////        mv.setViewName("/hello.html");    // 에러발생 : TemplateInputException
////        mv.setViewName("/templates/hello");      // 에러발생 : 브라우저 Whitelabel Error Page
////        mv.setViewName("/templates/hello.html"); // 에러발생 : TemplateInputException
//        return mv;
//    }

/*************** ajax 방법 시도 ***************/

    // ajax 정상작동 테스트용
    @RequestMapping(value = "/ga4")
    public String getAnalyticsDataAjax(GoogleAnalytics4VO ga4Vo, Model model) {
        ga4Vo.setTodayVisitors(service.ga4CacheToday());
        ga4Vo.setAllVisitors(service.ga4CacheAll());
        model.addAttribute("ga4Vo",ga4Vo);

        return "/helloAjax";
    }
    // ajax 정상작동 테스트용
    @RequestMapping(value = "/ga4Visitor")
    @ResponseBody
    public GoogleAnalytics4VO getAnalyticsDataAjaxCall(GoogleAnalytics4VO ga4Vo, Model model) {
        ga4Vo.setTodayVisitors(service.ga4CacheToday());
        ga4Vo.setAllVisitors(service.ga4CacheAll());
        return ga4Vo;
    }


/*************** HttpServlet의 application Scope에 저장하는 방법 사용 ***************/
//// HttpServlet 및 HttpServletRequest가 interface라 호출 시, DI때문에 인스턴스 생성이 불가해서 막힌 상태
//    public String getTodayCnt(HttpServletRequest request, Model model){
//        ServletContext servletContext = request.getServletContext(); // 서블릿 3.0이상에서 HttpServletRequest에서 getServletContext() 바로 가능
////        ServletContext servletContext = this.getServletContext();    // 서블릿 3.0이하에서는  extends HttpServlet 후 this.getServletContext()로 가능
//        String todayCnt = String.valueOf(servletContext.getAttribute("todayCnt"));
//        if (todayCnt == null) {
//            todayCnt = service.ga4CacheToday();
//        }
//        return todayCnt;
//    }
//
//    public String getAllCnt(HttpServletRequest request, Model model){
//        ServletContext servletContext = request.getServletContext();
//        String allCnt = String.valueOf(servletContext.getAttribute("allCnt"));
//        if (allCnt == null) {
//            allCnt = service.ga4CacheAll();
//        }
//        return allCnt;
//    }

    // 실제 프로젝트 코드
    // 기획팀 요청한 세자리마다 , 추가
//    @RequestMapping(value = "/ND_ga4Visitor.do")
//    public String ga4DataAjaxCall(GoogleAnalytics4VO ga4Vo, Model model) {
//        String today = service.ga4CacheToday();
//        String all = service.ga4CacheAll();
//
//        // DecimalFormat : 세자리수마다 , 추가
//        DecimalFormat formatter = new DecimalFormat();
//        String formattedToday = formatter.format(Long.parseLong(today));
//        String formattedAll = formatter.format(Long.parseLong(all));
////          String formattedToday = String.valueOf(Long.parseLong(today));
////          String formattedAll = String.valueOf(Long.parseLong(all));
//
//        ga4Vo.setTodayVisitors(formattedToday);
//        ga4Vo.setAllVisitors(formattedAll);
//        return responseJson(model, ga4Vo); // 오픈웍스 BaseController의 responseJson()에서 model.addAttribute() 해줌
////          ga4Vo.setTodayVisitors(service.ga4CacheToday());
////          ga4Vo.setAllVisitors(service.ga4CacheAll());
////          return responseJson(model, ga4Vo); // BaseController의 responseJson()에서 model.addAttribute() 해줌
//    }

}
