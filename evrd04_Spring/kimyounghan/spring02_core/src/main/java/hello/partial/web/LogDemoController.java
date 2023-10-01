package hello.partial.web;

import hello.partial.common.MyLogger;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

// section9-6 request 스코프예제 만들기
//@Controller
//@RequiredArgsConstructor
//public class LogDemoController {
//    private final LogDemoService logDemoService;
//    private final MyLogger myLogger;
//
//    @RequestMapping("log-demo") // log-demo라는 요청이 오면 아래의 logDemo()를 실행하라는 의미
//    @ResponseBody   // 뷰화면 없이 문자를 바로 반환할거기에 @ResponseBody 사용
//    public String logDemo(HttpServletRequest request) {
//
//    String requestURL = request.getRequestURL().toString();
//        myLogger.setRequestURL(requestURL);
//    }
//}


// section9-7 스코프와 Provider
@Controller
@RequiredArgsConstructor
public class LogDemoController {
    private final LogDemoService logDemoService;
//    private final MyLogger myLogger;
    private final ObjectProvider<MyLogger> objectProvider;

    @RequestMapping("log-demo") // log-demo라는 요청이 오면 아래의 logDemo()를 실행하라는 의미
    @ResponseBody   // 뷰화면 없이 문자를 바로 반환할거기에 @ResponseBody 사용
    public String logDemo(HttpServletRequest request) {
        String requestURL = request.getRequestURL().toString();
        MyLogger myLogger = objectProvider.getObject();
        myLogger.setRequestURL(requestURL);
        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "ok";
    }
}



