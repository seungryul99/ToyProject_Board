package com.Board.demo.member.entity;

import com.Board.demo.article.entity.Article;
import com.Board.demo.comment.entity.Comment;
import com.Board.demo.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor (access = AccessLevel.PROTECTED)
@AllArgsConstructor (access = AccessLevel.PRIVATE)
public class Member extends BaseEntity {

    @Id
    @Column(name = "member_id")
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (unique = true)
    private String username;

    private String password;

    @Column (unique = true)
    private String nickname;

    @OneToMany (mappedBy = "member", cascade = CascadeType.ALL)
    List<Comment> comments = new ArrayList<>();

    @OneToMany (mappedBy = "member", cascade = CascadeType.ALL)
    List <Article> articles = new ArrayList<>();

}