package com.Board.demo.comment.repository;

import com.Board.demo.comment.entity.Comment;
import org.hibernate.annotations.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findCommentsByArticleId(Long article);

}
