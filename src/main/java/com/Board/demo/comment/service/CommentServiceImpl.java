package com.Board.demo.comment.service;

import com.Board.demo.article.repository.ArticleRepository;
import com.Board.demo.comment.entity.Comment;
import com.Board.demo.comment.repository.CommentRepository;
import com.Board.demo.comment.request.CommentCreateRequest;
import com.Board.demo.comment.request.CommentDeleteRequest;
import com.Board.demo.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;

    private final ArticleRepository articleRepository;

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public void addComment(CommentCreateRequest commentCreateRequest) {

        Comment comment = Comment.builder()
                .content(commentCreateRequest.getContent())
                .article(articleRepository.findById(commentCreateRequest.getArticleId()).get())
                .member(memberRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()))
                .build();


        commentRepository.save(comment);
    }

    @Override
    @Transactional
    public void deleteComment(CommentDeleteRequest commentDeleteRequest) {

        commentRepository.deleteById(commentDeleteRequest.getCommentId());
    }
}
