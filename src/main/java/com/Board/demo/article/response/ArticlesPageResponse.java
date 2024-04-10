package com.Board.demo.article.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArticlesPageResponse {

    private String nickname;

    private List<ArticleResponse> articles;

    private int currnetPageNumber;

    private int currentPageSize;

    private int totalPages;

}
