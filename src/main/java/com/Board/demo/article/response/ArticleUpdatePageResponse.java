package com.Board.demo.article.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArticleUpdatePageResponse {

    private String title;

    private String content;

    private Long articleId;
}
