package com.Board.demo.handler;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import java.io.IOException;

public class LoginFailManager extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        String errorCode;

        
        // 임시로 1이면 로그인 시도 실패 예외, 2면 알수없는 오류로 지정해둠
        if (exception instanceof BadCredentialsException) {
            errorCode = "1";
        } else errorCode = "2";

        setDefaultFailureUrl("/login?error=" + errorCode);

        super.onAuthenticationFailure(request, response, exception);

    }
}