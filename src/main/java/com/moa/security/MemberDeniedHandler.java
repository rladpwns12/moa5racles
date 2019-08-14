package com.moa.security;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberDeniedHandler implements AccessDeniedHandler {
    private static final String DENY_MESSAGE = "member";
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        e.printStackTrace();
        httpServletRequest.getParameterMap().forEach((k,v)-> System.out.println(k+"/"+v));
        System.out.println(httpServletRequest.getParameterMap());
        httpServletRequest.setAttribute("denyMessage", DENY_MESSAGE);
        httpServletResponse.sendRedirect("/main");
    }
}
