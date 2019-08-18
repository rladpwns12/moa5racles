package com.moa.security;

import com.moa.message.PathMessage;
import com.moa.message.RoleMessage;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Locale;

@Log4j
public class AdminSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    MessageSource messageSource;
    //session add from db
    // dispatch
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        Collection<? extends GrantedAuthority> authorities
                = authentication.getAuthorities();
        boolean isAdmin = false;
        for (GrantedAuthority grantedAuthority : authorities)
            if (grantedAuthority.getAuthority().equals(RoleMessage.ROLE_ADMIN)) {
                isAdmin = true;
                break;
            }
        if(!isAdmin){
            new SecurityContextLogoutHandler().logout(httpServletRequest,httpServletResponse,authentication);
            httpServletRequest.setAttribute(RoleMessage.ERRORMSG,messageSource.getMessage("login.incorrect",null, Locale.getDefault()));
            httpServletRequest.getRequestDispatcher("/admin/login?error").forward(httpServletRequest,httpServletResponse);
        }
        else
            httpServletResponse.sendRedirect(PathMessage.ADMIIN);

    }
}