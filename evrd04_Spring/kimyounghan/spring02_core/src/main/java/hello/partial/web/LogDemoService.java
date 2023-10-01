package hello.partial.web;


import hello.partial.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

// section9-6 request 스코프예제 만들기
//@Service
//@RequiredArgsConstructor
//public class LogDemoService {
//    private final MyLogger myLogger;
//
//    public void logic(String id) {
//        myLogger.log("service id = " + id);
//    }
//}


// section9-7 스코프와 Provider
@Service
@RequiredArgsConstructor
public class LogDemoService {
//    private final MyLogger myLogger;
    private final ObjectProvider<MyLogger> objectProvider;

    public void logic(String id) {
        MyLogger myLogger = objectProvider.getObject();
        myLogger.log("service id = " + id);
    }
}
