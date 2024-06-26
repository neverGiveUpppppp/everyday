package hello.core5.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MyLogger {
    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String msg) {
        System.out.println("[" + uuid + "]" + " - " + "[" + requestURL + "]");
    }

    @PostConstruct
    public void init(){
        uuid = UUID.randomUUID().toString(); // 이 빈이 생성되는 시점에 자동으로 @PostConstruct 초기화 메서드를 사용해서 uuid를 생성해서 저장해둔다. 이 빈은 HTTP 요청당 하나씩 생성되므로, uuid를 저장해두면 다른 HTTP 요청과 구분 가능해짐
        System.out.println("[" + uuid + "] request scope bean create:" + this);

    }

    @PreDestroy
    public void close(){
        System.out.println("[" + uuid + "] request scope bean close:" + this);
    }
}
