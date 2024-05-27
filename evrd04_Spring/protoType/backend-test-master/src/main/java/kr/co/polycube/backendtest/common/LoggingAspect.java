package kr.co.polycube.backendtest.common;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class LoggingAspect {

    private final HttpServletRequest request;

    @Before("execution(* kr.co.polycube.backendtest.user.UserController.*(..))")
    public void logUserAgent() {
        String userAgent = request.getHeader("User-Agent");
        log.info("Client Agent: " + userAgent);
    }

}
