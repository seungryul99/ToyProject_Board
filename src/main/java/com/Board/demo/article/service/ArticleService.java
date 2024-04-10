package com.Board.demo.article.service;

import com.Board.demo.article.response.ArticlesPageResponse;

public interface ArticleService {
    ArticlesPageResponse getArticlesPage(int page, int size);
}
