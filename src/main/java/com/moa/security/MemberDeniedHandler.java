package com.moa.security;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

import static com.moa.message.PathMessage.ACCESS_DENIED;
import static com.moa.message.RoleMessage.*;

@Log4j
public class MemberDeniedHandler implements AccessDeniedHandler {
    @Autowired
    private MessageSource messageSource;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        String URIPath=request.getRequestURI();
        request.setAttribute("errorMessage",CheckRegExp(URIPath,request));
        request.getRequestDispatcher(ACCESS_DENIED).forward(request,response);
    }

    private String CheckRegExp(String uriPath, HttpServletRequest request) {
        log.info("URIPath : " + uriPath);
        if (uriPath.matches(REGISTHOST)) {
            if (request.isUserInRole(ROLE_HOST))
                return messageSource.getMessage("accessDenied.alreadyHost", null, Locale.getDefault());
            else
                return messageSource.getMessage("accessDenied.reviewHost", null, Locale.getDefault());
        }
        if (uriPath.matches(HOSTPAGE)) {
            if (request.isUserInRole(ROLE_USER))
                return messageSource.getMessage("accessDenied.host", null, Locale.getDefault());
            else
                return messageSource.getMessage("accessDenied.reviewHost", null, Locale.getDefault());
        }
        if (uriPath.matches(KEEP)) {
            if (request.isUserInRole(ROLE_PRE_HOST))
                return messageSource.getMessage("accessDenied.reviewHost", null, Locale.getDefault());
            else
                return messageSource.getMessage("accessDenied.host", null, Locale.getDefault());
        }
        if (uriPath.matches(ADMIN))
            return messageSource.getMessage("accessDenied.admin", null, Locale.getDefault());
        return messageSource.getMessage("accessDenied.default", null, Locale.getDefault());
    }
}
