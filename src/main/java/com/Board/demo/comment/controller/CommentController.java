package com.Board.demo.comment.controller;

import com.Board.demo.comment.request.CommentCreateRequest;
import com.Board.demo.comment.service.CommentService;
import com.Board.demo.member.entity.Member;
import com.Board.demo.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    private final MemberService memberService;


    @PostMapping("/addComment")
    public String addComment(@Validated @ModelAttribute CommentCreateRequest commentCreateRequest){


        commentCreateRequest.setWriter(memberService.getCurrentLoginUserNickname());

        commentService.addComment(commentCreateRequest);

        return "redirect:/article/"+commentCreateRequest.getArticleId();
    }

}
