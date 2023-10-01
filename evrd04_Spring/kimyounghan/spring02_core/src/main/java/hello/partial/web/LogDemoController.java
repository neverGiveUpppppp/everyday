package hello.partial.web;

import hello.partial.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

// section9-6 request 스코프예제 만들기
@Controller
@RequiredArgsConstructor
public class LogDemoController {
    private final LogDemoService logDemoService;
    private final MyLogger myLogger;

    @RequestMapping("log-demo") // log-demo라는 요청이 오면 아래의 logDemo()를 실행하라는 의미
    @ResponseBody   // 뷰화면 없이 문자를 바로 반환할거기에 @ResponseBody 사용
    public String logDemo(HttpServletRequest request) {

    String requestURL = request.getRequestURL().toString();
        myLogger.setRequestURL(requestURL);
    }
}
