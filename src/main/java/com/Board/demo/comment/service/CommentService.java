package com.Board.demo.comment.service;

import com.Board.demo.comment.request.CommentCreateRequest;
import com.Board.demo.comment.request.CommentDeleteRequest;

public interface CommentService {
    void addComment(CommentCreateRequest commentCreateRequest);

    void deleteComment(CommentDeleteRequest commentDeleteRequest);
}
