package com.Board.demo.article.repository;

import com.Board.demo.article.entity.Article;
import com.Board.demo.article.response.ArticleResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {


}