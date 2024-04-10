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

        
        // 시큐리티에서 로그인 에러시 어떤 일이 일어나는 지는 잘 모르겠는데 어떤 오류가 터져도 BadCredentialsException 외에는 모두 hide 처리하길래
        // 임시로 1이면 로그인 시도 실패 예외, 2면 알수없는 오류로 지정해둠, 수정 되어야 할 부분
        if (exception instanceof BadCredentialsException) {
            errorCode = "1";
        } else errorCode = "2";

        setDefaultFailureUrl("/login?error=" + errorCode);

        super.onAuthenticationFailure(request, response, exception);

    }
}