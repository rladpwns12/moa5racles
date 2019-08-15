package com.moa.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;

public class UserSuccessHandler implements AuthenticationSuccessHandler {

    //session add from db
    // dispatch
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        Collection<? extends GrantedAuthority> authorities
                = authentication.getAuthorities();

//        for (GrantedAuthority grantedAuthority : authorities) {
//            if (grantedAuthority.getAuthority().equals("ROLE_MEMBER")) {
//                System.out.println("role memnner 님");
//            } else {
//                System.out.println("role memnner 아님");
//            }
//        }
        System.out.println("UserSuccessHnadler...");

    }
}
