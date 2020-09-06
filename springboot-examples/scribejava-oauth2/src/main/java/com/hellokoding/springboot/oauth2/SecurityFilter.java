package com.hellokoding.springboot.oauth2;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.util.WebUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Component
@WebFilter("/*")
public class SecurityFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        if ("GET".equalsIgnoreCase(httpServletRequest.getMethod())) {
            Cookie cookie = new Cookie("XSRF-TOKEN", UUID.randomUUID().toString());
            cookie.setHttpOnly(false);
            httpServletResponse.addCookie(cookie);
        } else if ("POST".equalsIgnoreCase(httpServletRequest.getMethod())) {
            Cookie cookie = WebUtils.getCookie(httpServletRequest, "XSRF-TOKEN");
            String csrfHeader = httpServletRequest.getHeader("X-XSRF-TOKEN");
            if (!Objects.equals(csrfHeader, cookie.getValue())) {
                httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());
                return;
            }
        }

        chain.doFilter(request, response);
    }
}
