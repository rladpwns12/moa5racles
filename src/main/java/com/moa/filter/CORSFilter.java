package com.moa.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;



public class CORSFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;


        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with");

        //허용되는 도메인
        response.setHeader("Access-Control-Allow-Origin", "http://5racle.powerlinux.co.kr");
        response.setHeader("Access-Control-Allow-Origin", "http://powerlinux.co.kr");
        response.setHeader("Access-Control-Allow-Origin", "https://www.coolsms.co.kr");
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:8089");

        //전체 모든 도메인을 허용할 경우
//        response.setHeader("Access-Control-Allow-Origin", "*");

        filterChain.doFilter(servletRequest, servletResponse);

    }
}
