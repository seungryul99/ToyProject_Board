package com.Board.demo.member.service;

import com.Board.demo.member.entity.Member;
import com.Board.demo.member.request.SignUpRequest;

public interface MemberService {

    void signup(SignUpRequest signUpRequest);


    Boolean checkDuplicateUsername(String username);


    Boolean checkDuplicateNickname(String nickname);

    String getCurrentLoginUsername();
}