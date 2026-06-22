package com.kopo.portal.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 로그인 여부를 확인하는 인터셉터.
 * 세션에 로그인한 학생 정보가 없으면 로그인 페이지로 보낸다.
 * (Spring Security를 아직 배우지 않은 단계라, HttpSession 기반으로 직접 구현했다.)
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        HttpSession session = request.getSession();
        if (session.getAttribute("loginStudent") != null) {
            return true;
        }
        try {
            response.sendRedirect("/login");
        } catch (Exception ignored) {
        }
        return false;
    }
}
