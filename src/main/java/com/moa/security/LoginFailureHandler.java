package com.moa.security;

import lombok.Data;
import lombok.extern.log4j.Log4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Data
@Log4j
public class LoginFailureHandler implements AuthenticationFailureHandler {
    private static final String ID_PW_INCORRECT_MESSAGE = "아이디나 비밀번호가 맞지 않습니다. 다시 확인해주세요.";
    private static final String INACTIVE_USER_MESSAGE = "계정이 비활성화 되었습니다. 관리자에게 문의하세요.";
    private static final String VALIDATE_UNTIL_MESSAGE = "회원정보 유효기간이 지났습니다. 관리자에게 문의하세요.";

    private String username;
    private String password;
    private String errorMessage;
    private String defaultFailureUrl;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        String username = request.getParameter(this.username);
        String password = request.getParameter(this.password);
        String errorMessage = null;

        if(e instanceof BadCredentialsException) {
            errorMessage = ID_PW_INCORRECT_MESSAGE;
        } else if(e instanceof InternalAuthenticationServiceException) {
            errorMessage = ID_PW_INCORRECT_MESSAGE;
        } else if(e instanceof DisabledException) {
            errorMessage = INACTIVE_USER_MESSAGE;
        } else if(e instanceof CredentialsExpiredException) {
            errorMessage = VALIDATE_UNTIL_MESSAGE;
        }

        request.setAttribute(this.username, username);
        request.setAttribute(this.errorMessage, errorMessage);

        request.getRequestDispatcher(defaultFailureUrl).forward(request, response);
    }
}
