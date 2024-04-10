package com.Board.demo.article.response;

import com.Board.demo.comment.response.CommentResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArticlePageResponse {

    private Long articleId;

    private String title;

    private String author;

    private String content;

    private boolean canUpdate;

    private List<CommentResponse> comments;
}
