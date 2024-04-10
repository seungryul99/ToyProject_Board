package com.Board.demo.handler;

import com.Board.demo.member.exception.MemberNicknameDuplicateException;
import com.Board.demo.member.exception.MemberUsernameDuplicateException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice(annotations = Controller.class)
public class ExceptionManager {

    @ExceptionHandler(MemberUsernameDuplicateException.class)
    public ModelAndView MemberUsernameDuplicateExceptionHandle(){

        ModelAndView mav = new ModelAndView();
        mav.addObject("usernameDuplicate", "동일한 아이디가 이미 존재합니다.");
        mav.setViewName("signup");

        return mav;
    }

    @ExceptionHandler (MemberNicknameDuplicateException.class)
    public ModelAndView MemberNicknameDuplicateExceptionHandle(){

        ModelAndView mav = new ModelAndView();
        mav.addObject("userNicknameDuplicate", "동일한 닉네임이 이미 존재합니다.");
        mav.setViewName("signup");

        return mav;
    }
}