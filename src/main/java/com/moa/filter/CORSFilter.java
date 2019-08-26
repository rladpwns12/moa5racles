package com.moa.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.moa.message.PathMessage;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Component;


@Log4j
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
        HttpServletRequest request = (HttpServletRequest) servletRequest ;
        deleteFileCnt(request);
        if(excludeUrl(request)){
            filterChain.doFilter(servletRequest, servletResponse); //걸러내는 URI일 경우 요청값 그대로 처리
        }else{
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
    private boolean excludeUrl(HttpServletRequest request) {
        String uri = request.getRequestURI().toString().trim();
        if (uri.startsWith(PathMessage.STOREBOARD)||uri.startsWith(PathMessage.ENTRUST)) {
            return true;
        } else {
            return false;
        }
    }
    private void deleteFileCnt(HttpServletRequest request){
        String uri = request.getRequestURI().toString().trim();
        if(!(uri.startsWith(PathMessage.UPLOAD) || uri.startsWith(PathMessage.DISPLAY)||uri.startsWith(PathMessage.DELETE_FILE))) {
            if (uri.contains(PathMessage.KEEP)||uri.startsWith(PathMessage.ENTRUST))
                return;
            HttpSession session = request.getSession();
            session.removeAttribute("fileCnt");
        }
    }
}
