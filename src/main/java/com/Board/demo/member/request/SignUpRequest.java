package com.Board.demo.member.request;

import com.Board.demo.member.entity.Member;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {

    private String username;

    private String password;

    private String nickname;


}