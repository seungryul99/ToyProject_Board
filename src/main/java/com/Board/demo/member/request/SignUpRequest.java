package com.Board.demo.member.request;

import com.Board.demo.member.entity.Member;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {

    @Pattern(regexp = "^[a-zA-Z0-9]{8,16}$", message = "길이 8~16, 알파벳+숫자만 가능")
    private String username;

    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&+=])(?=\\S+$).{8,16}$", message = "길이 8~16, 알파벳 대소문자, 특수문자, 숫자 포함")
    private String password;


    @Pattern(regexp = "^[a-zA-Z0-9가-힣]{6,16}$", message = "길이 6~16, 알파벳+숫자+한국어만 가능")
    private String nickname;

}