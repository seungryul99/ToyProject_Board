package com.Board.demo.security.service;

import com.Board.demo.member.entity.Member;
import com.Board.demo.member.repository.MemberRepository;
import com.Board.demo.security.dto.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = memberRepository.findByUsername(username);

        if(member != null){

            return new UserDetailsImpl(member);
        }
        else {

            throw new UsernameNotFoundException(username);
        }
    }
}