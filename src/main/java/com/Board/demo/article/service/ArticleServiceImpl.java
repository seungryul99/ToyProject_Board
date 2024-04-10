package com.Board.demo.article.service;

import com.Board.demo.article.entity.Article;
import com.Board.demo.article.repository.ArticleRepository;
import com.Board.demo.article.request.ArticleCreateRequest;
import com.Board.demo.article.request.ArticleDeleteRequest;
import com.Board.demo.article.request.ArticleUpdateRequest;
import com.Board.demo.article.response.ArticlePageResponse;
import com.Board.demo.article.response.ArticleResponse;
import com.Board.demo.article.response.ArticleUpdatePageResponse;
import com.Board.demo.article.response.ArticlesPageResponse;
import com.Board.demo.comment.entity.Comment;
import com.Board.demo.comment.repository.CommentRepository;
import com.Board.demo.comment.response.CommentResponse;
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

    private final CommentRepository commentRepository;

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

    @Override
    public ArticleUpdatePageResponse getArticle(Long articleId) {

        ArticleUpdatePageResponse articleUpdatePageResponse = new ArticleUpdatePageResponse();
        Article article = articleRepository.findById(articleId).get();

        articleUpdatePageResponse.setTitle(article.getTitle());
        articleUpdatePageResponse.setContent(article.getContent());
        articleUpdatePageResponse.setArticleId(articleId);


        return articleUpdatePageResponse;
    }



    @Override
    public String getAuthor(Long articleId) {
        return articleRepository.findById(articleId).get().getMember().getNickname();
    }

    @Override
    @Transactional
    public void updateArticle(ArticleUpdateRequest articleUpdateRequest) {
        Article article = Article.builder()
                .id(articleUpdateRequest.getArticleId())
                .title(articleUpdateRequest.getTitle())
                .content(articleUpdateRequest.getContent())
                .member(memberRepository.findById(articleUpdateRequest.getMemberId()).get()).build();

        articleRepository.save(article);
    }

    @Override
    @Transactional
    public void deleteArticle(ArticleDeleteRequest articleDeleteRequest) {

        articleRepository.deleteById(articleDeleteRequest.getArticleId());
    }

    @Override
    public ArticlePageResponse readArticle(Long articleId, String currentMember) {

        ArticlePageResponse articlePageResponse = new ArticlePageResponse();
        Article article = articleRepository.findById(articleId).get();

        articlePageResponse.setArticleId(articleId);
        articlePageResponse.setTitle(article.getTitle());
        articlePageResponse.setAuthor(article.getMember().getNickname());
        articlePageResponse.setContent(article.getContent());

        if(currentMember!=null && currentMember.equals(article.getMember().getUsername())){
            articlePageResponse.setCanUpdate(true);
        }

        List<Comment> comments = commentRepository.findCommentsByArticleId(articleId);
        List<CommentResponse> commentsResponse = new ArrayList<>();

        for (Comment x : comments){
            CommentResponse commentResponse = new CommentResponse(x.getMember().getNickname(),x.getContent(),false);

            if (currentMember.equals(x.getMember().getNickname())){
                commentResponse.setCanDelete(true);
            }

            commentsResponse.add(commentResponse);
        }

        articlePageResponse.setComments(commentsResponse);

        return articlePageResponse;
    }


}
