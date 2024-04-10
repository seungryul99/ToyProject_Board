package com.Board.demo.article.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArticleResponse {

    private Long articleId;

    private String title;

    private String author;
}
