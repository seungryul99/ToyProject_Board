package com.Board.demo.article.controller;


import com.Board.demo.article.request.ArticleCreateRequest;
import com.Board.demo.article.response.ArticlesPageResponse;
import com.Board.demo.article.service.ArticleService;
import com.Board.demo.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    private final MemberService memberService;


    @GetMapping("/")
    public String indexPage(){
        return "redirect:/articles";
    }


    @GetMapping("/articles")
    public String articlePage(@RequestParam(defaultValue = "1", name = "page") int page,
                              @RequestParam(defaultValue = "15", name = "size") int size,
                              Model model){

        // 이렇게 안하면 articleService 에서 MemberService를 이용하게 되는데 DTO 설계부터 틀린거 같긴한데, 이정도 트레이드 오프는 해줘도 되지 않나 라는 생각이 들었음
        ArticlesPageResponse articlesPageResponse = articleService.getArticlesPage(page, size);
        articlesPageResponse.setUsername(memberService.getCurrentLoginUsername());

        model.addAttribute("response",articlesPageResponse);

        return "articles";
    }

    @GetMapping("/articleCreateForm")
    public String articleCreateFormPage(){

        return "articleCreateForm";
    }

    @PostMapping("/article")
    public String articleCreate(@Validated @ModelAttribute ArticleCreateRequest articleCreateRequest, BindingResult bindingResult, Model model){


        if (bindingResult.hasErrors()) {
            for (FieldError error : bindingResult.getFieldErrors()) {
                model.addAttribute(error.getField() + "Error", error.getDefaultMessage());
            }
            return "articleCreateForm";
        }

        String author = memberService.getCurrentLoginUsername();
        articleService.writeArticle(articleCreateRequest,author);

        return "redirect:/articles";
    }


}
