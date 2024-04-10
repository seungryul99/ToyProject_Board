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
public class AllArticlesPageResponse {

    private String username;

    private List<ArticleResponse> articles;

    private Long currnetPageNumber;

    private Long currentPageSize;

    private Long totalPages;

}
