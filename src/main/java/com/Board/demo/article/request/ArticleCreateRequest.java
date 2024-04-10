package com.Board.demo.article.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArticleCreateRequest {

    private Long memberId;

    private String title;

    private String content;
}
