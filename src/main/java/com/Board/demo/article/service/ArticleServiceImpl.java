package com.Board.demo.article.service;

import com.Board.demo.article.entity.Article;
import com.Board.demo.article.repository.ArticleRepository;
import com.Board.demo.article.request.ArticleCreateRequest;
import com.Board.demo.article.response.ArticleResponse;
import com.Board.demo.article.response.ArticlesPageResponse;
import com.Board.demo.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService{

    private final ArticleRepository articleRepository;

    private final MemberRepository memberRepository;


    @Override
    public ArticlesPageResponse getArticlesPage(int page, int size) {

        Pageable pageable = PageRequest.of(page-1,size, Sort.by(Sort.Direction.DESC,"createTime"));


        // 쓰레기 코드가 나온거 같음..

        ArticlesPageResponse articlesPageResponse = new ArticlesPageResponse();

        Page<Article> articlePage = articleRepository.findAll(pageable);
        List<ArticleResponse> articles = new ArrayList<>();

        for (Article x : articlePage){
            ArticleResponse articleResponse = new ArticleResponse(x.getId(),x.getTitle(), x.getMember().getNickname());
            articles.add(articleResponse);
        }
        articlesPageResponse.setArticles(articles);

        articlesPageResponse.setCurrnetPageNumber(pageable.getPageNumber());
        articlesPageResponse.setCurrentPageSize(pageable.getPageSize());
        articlesPageResponse.setTotalPages(articlePage.getTotalPages());

        return articlesPageResponse;
    }

    @Override
    @Transactional
    public void writeArticle(ArticleCreateRequest articleCreateRequest, String author) {

        Article article = Article.builder()
                .member(memberRepository.findByUsername(author))
                .title(articleCreateRequest.getTitle())
                .content(articleCreateRequest.getContent())
                .build();

        articleRepository.save(article);
    }
}
