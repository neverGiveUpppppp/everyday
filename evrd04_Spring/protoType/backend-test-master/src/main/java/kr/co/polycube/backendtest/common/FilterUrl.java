package kr.co.polycube.backendtest.common;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Pattern;

public class FilterUrl implements Filter {

    // 허용되지 않는 특수문자 패턴 정의
    private static final Pattern DISALLOWED_PATTERN = Pattern.compile("[^a-zA-Z0-9?&=:.//]"); // 정규식 사용

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String query = httpRequest.getQueryString();

        if (query != null && DISALLOWED_PATTERN.matcher(query).find()) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            httpResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, "url에 ?, &, =, :, //를 제외한 특수문자는 사용이 불가능합니다.");
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}