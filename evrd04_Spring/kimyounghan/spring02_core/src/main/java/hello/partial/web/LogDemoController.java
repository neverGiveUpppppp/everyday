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


//// section9-7 스코프와 Provider
//@Controller
//@RequiredArgsConstructor
//public class LogDemoController {
//    private final LogDemoService logDemoService;
////    private final MyLogger myLogger;
//    private final ObjectProvider<MyLogger> objectProvider;
//
//    @RequestMapping("log-demo") // log-demo라는 요청이 오면 아래의 logDemo()를 실행하라는 의미
//    @ResponseBody   // 뷰화면 없이 문자를 바로 반환할거기에 @ResponseBody 사용
//    public String logDemo(HttpServletRequest request) {
//        String requestURL = request.getRequestURL().toString();
//        MyLogger myLogger = objectProvider.getObject();
//        myLogger.setRequestURL(requestURL);
//        myLogger.log("controller test");
//        logDemoService.logic("testId");
//        return "ok";
//    }
//}

// section9-8 스코프와 프록시 : 9-6 상태로 롤백하고 MyLogger에서 @Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)만 추가
@Controller
@RequiredArgsConstructor
public class LogDemoController { // 로거 작동 확인 테스트용 컨트롤러

    private final LogDemoService logDemoService;
    private final MyLogger myLogger;

    @RequestMapping("log-demo") // log-demo라는 요청이 오면 아래의 logDemo()를 실행하라는 의미
    @ResponseBody   // 뷰화면 없이 문자를 바로 반환할거기에 @ResponseBody 사용
    public String logDemo(HttpServletRequest request) throws InterruptedException { // throws InterruptedException는 검증용 Thread로 인한 생성
        String requestURL = request.getRequestURL().toString(); // 고객이 어떤 url로 요청했는지 알기 위한 request.getRequestURL()
        System.out.println("myLogger = " + myLogger); //
        myLogger.setRequestURL(requestURL); // MyLogger클래스의 requestURL 필드값 저장하기 // 이 예제의 목표인 로그 생성 중에 [http://localhost:8080/log-demo] 이부분을 찍기위함

        myLogger.log("controller test");    // 컨트롤러에서 print 찍기
        Thread.sleep(1000); // 동시에 요청4개가 오면, 4개가 myLogger.log(”controller test”);에서 기다리게 됨
        logDemoService.logic("testId");  // 서비스에서 pring 찍기 // 가보면 myLogger.log() 사용임
        return "OK";
    }
}



