package com.Board.demo.comment.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {

    private Long commentId;

    private String writer;

    private String content;

    private boolean canDelete;
}
