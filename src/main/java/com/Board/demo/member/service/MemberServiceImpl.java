package com.Board.demo.member.service;

import com.Board.demo.member.entity.Member;
import com.Board.demo.member.exception.MemberNicknameDuplicateException;
import com.Board.demo.member.exception.MemberUsernameDuplicateException;
import com.Board.demo.member.repository.MemberRepository;
import com.Board.demo.member.request.SignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    @Override
    public void signup(SignUpRequest signUpRequest) {

        if(checkDuplicateUsername(signUpRequest.getUsername())) {
            throw new MemberUsernameDuplicateException();
        }
        if(checkDuplicateNickname(signUpRequest.getNickname())){
            throw new MemberNicknameDuplicateException();
        }

        Member member = Member.builder()
                .username(signUpRequest.getUsername())
                .password(bCryptPasswordEncoder.encode(signUpRequest.getPassword()))
                .nickname(signUpRequest.getNickname())
                .build();


        memberRepository.save(member);
    }

    @Override
    public Boolean checkDuplicateUsername(String username) {
        return memberRepository.existsByUsername(username);
    }

    @Override
    public Boolean checkDuplicateNickname(String nickname) {
        return memberRepository.existsByNickname(nickname);
    }

    @Override
    public String getCurrentLoginUsername(){

        if (SecurityContextHolder.getContext().getAuthentication() == null){
            return null;
        }

        return SecurityContextHolder.getContext().getAuthentication().getName();
    }


}