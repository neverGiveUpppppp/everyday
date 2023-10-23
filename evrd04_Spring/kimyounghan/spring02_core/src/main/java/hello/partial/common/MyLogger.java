package hello.partial.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.UUID;

//// section9-6 request 스코프예제 만들기
//public class MyLogger {
//    private String uuid;
//    private String requestURL;
//
//    public void setRequestURL(String requestURL) {
//        this.requestURL = requestURL;
//    }
//
//    public void log(String message) {
//        System.out.println("[" + uuid + "]" + "[" + requestURL + "] " + message);
//    }
//
//    @PostConstruct
//    public void init(){
//        uuid = UUID.randomUUID().toString(); // 이 빈이 생성되는 시점에 자동으로 @PostConstruct 초기화 메서드를 사용해서 uuid를 생성해서 저장해둔다. 이 빈은 HTTP 요청당 하나씩 생성되므로, uuid를 저장해두면 다른 HTTP 요청과 구분 가능해짐
//        System.out.println("[" + uuid + "] request scope bean create:" + this);
//    }
//    @PreDestroy
//    public void close(){
//        System.out.println("[" + uuid + "] request scope bean close:" + this);
//    }
//
//}



// section9-8 스코프와 프록시
@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS) // section9-8 스코프와 프록시 : 추가 - , proxyMode = ScopedProxyMode.TARGET_CLASS
public class MyLogger { // 로그 출력을 위한 클래스
    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL){
        this.requestURL = requestURL; // requestURL은 이 빈이 생성되는 시점에는 알 수 없으므로, 외부에서 setter로 입력받는다.
    }

    public void log(String message) {
        System.out.println("[" + uuid + "]" + "[" + requestURL + "] " + message);
    }

    @PostConstruct
    public void init(){ // 고객 요청이 들어와서 최초로 호출이 필요하다고 스프링에 달라고 할 때, init()을 호출함
        uuid = UUID.randomUUID().toString(); // 이 빈이 생성되는 시점에 자동으로 @PostConstruct 초기화 메서드를 사용해서 uuid를 생성해서 저장해둔다. 이 빈은 HTTP 요청당 하나씩 생성되므로, uuid를 저장해두면 다른 HTTP 요청과 구분 가능해짐
        System.out.println("[" + uuid + "] request scope bean create:" + this);
    }
    @PreDestroy     // request Scope는 destroy 호출됨
    public void close() {  // 고객 요청이 빠져나갈 때, close()가 호출되면서 빈이 소멸됨
        System.out.println("[" + uuid + "] request scope bean close:" + this);
    }
}
