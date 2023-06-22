package com.blog.controller;

import com.blog.domain.Article;
import com.blog.dto.ArticleListViewResponse;
import com.blog.dto.ArticleViewResponse;
import com.blog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/articles")
public class BlogViewController {
    private final BlogService blogService;

    @GetMapping
    public String getArticles(Model model) {
        List<ArticleListViewResponse> articles = blogService.findAll().stream()
                .map(ArticleListViewResponse::new)
                .toList();

        // TODO: pagination
        
        model.addAttribute("articles", articles);
        return "articleList";
    }

    @GetMapping("/{id}")
    public String getArticle(@PathVariable Long id, Model model) {
        Article article = blogService.findById(id);
        model.addAttribute("article", new ArticleViewResponse(article));
        return "article";
    }

    @GetMapping("/new")
    public String newArticle(Model model){
        Article article = Article.builder().build();
        model.addAttribute("article", article);
        return "newArticleForm";
    }

    @GetMapping("/{id}/edit")
    public String editArticle(@PathVariable Long id, Model model) {
        Article article = blogService.findById(id);
        model.addAttribute("article", article);
        return "editArticleForm";
    }
}
