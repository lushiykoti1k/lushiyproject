package com.boots.controller;
import java.util.ArrayList;
import java.util.List;
import com.boots.entity.Article;
import com.boots.entity.User;
import com.boots.service.ArticleService;
import com.boots.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class ArticleController {
    private final ArticleService articleService;

    private final UserService userService;
    public ArticleController(ArticleService articleService, UserService userService) {
        this.articleService = articleService;
        this.userService = userService;
    }

    @PostMapping ("/article/create")
    public String createArticle(@ModelAttribute ("articleForm") @Valid Article articleForm) {
        articleForm.setAuthor( userService.findUserById(userService.getCurrentUserById()));
        articleService.createArticle(articleForm);
        return "redirect:/article";
    }

    @PostMapping ("/article/delete/{id}")
    public String deleteArticle(@PathVariable("id") Long id) {
        articleService.deleteArticleById(id);
        return "redirect:/article";
    }
    @GetMapping("/news")
    public String getMainPage(Model model) {

        return "news";
    }
    @GetMapping("/article")
    public String getArticles(Model model) {
        Long user = userService.getCurrentUserById();
        List<Article> articles = new ArrayList<>();
        articles.addAll(userService.findUserById(user).getArticles());
        model.addAttribute("allArticles", articles);
        model.addAttribute("articleForm", new Article());
        return "article";
    }

    @PostMapping("/article/update/{id}")
    public String updateArticle(@PathVariable Long id, @RequestParam("updatename") String updateForm) {
        articleService.updateArticle(id, updateForm);
        return "redirect:/article";
    }
}
