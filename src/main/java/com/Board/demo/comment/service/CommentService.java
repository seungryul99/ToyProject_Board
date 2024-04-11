package com.Board.demo.comment.service;

import com.Board.demo.comment.request.CommentCreateRequest;

public interface CommentService {
    void addComment(CommentCreateRequest commentCreateRequest);
}
