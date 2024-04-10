package com.Board.demo.article.service;

import com.Board.demo.article.request.ArticleCreateRequest;
import com.Board.demo.article.request.ArticleUpdateRequest;
import com.Board.demo.article.response.ArticleUpdatePageResponse;
import com.Board.demo.article.response.ArticlesPageResponse;

public interface ArticleService {
    ArticlesPageResponse getArticlesPage(int page, int size);

    void writeArticle(ArticleCreateRequest articleCreateRequest, String author);

    ArticleUpdatePageResponse getArticle(Long articleId);

    String getAuthor(Long articleId);

    void updateArticle(ArticleUpdateRequest articleUpdateRequest);
}
