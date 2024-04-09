package com.Board.demo.comment.entity;

import com.Board.demo.article.entity.Article;
import com.Board.demo.common.entity.BaseEntity;
import com.Board.demo.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@NoArgsConstructor (access = AccessLevel.PROTECTED)
@AllArgsConstructor (access = AccessLevel.PRIVATE)
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "comment_id")
    private Long id;

    @Lob
    private String content;


    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "member_id")
    private Member member;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "article_id")
    private Article article;

}