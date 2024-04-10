package com.Board.demo.member.controller;

import com.Board.demo.member.request.SignUpRequest;
import com.Board.demo.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    @GetMapping("/signup")
    public String signupPage(){

        return "signup";
    }

    @PostMapping("/signup")
    public String signup(@Validated @ModelAttribute SignUpRequest signUpRequest, BindingResult bindingResult, Model model){

        if (bindingResult.hasErrors()){

            for (FieldError error : bindingResult.getFieldErrors()){
                model.addAttribute(error.getField() + "Error", error.getDefaultMessage());
            }

            return "signup";
        }

        memberService.signup(signUpRequest);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(name = "error", required = false)String error, Model model){

        if(error!=null) model.addAttribute("error", "아이디나 비밀번호를 다시 확인하세요.");

        return "login";
    }

}