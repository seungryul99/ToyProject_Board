package com.Board.demo.article.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArticleUpdateRequest {

    private Long articleId;

    private Long memberId;

    @NotBlank(message = "제목은 비워둘 수 없습니다.")
    @Size(max = 255, message = "제목은 255자 이상일 수 없습니다.")
    private String title;

    @NotBlank(message = "내용은 비워둘 수 없습니다.")
    private String content;
}
