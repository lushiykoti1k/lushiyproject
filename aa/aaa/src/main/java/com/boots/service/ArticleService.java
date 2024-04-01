package com.boots.service;

import com.boots.entity.Article;
import com.boots.entity.User;
import com.boots.repository.ArticleRepository;
import org.springframework.boot.autoconfigure.jms.artemis.ArtemisAutoConfiguration;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ArticleService {
private final ArticleRepository articleRepository;
private final UserService userService;
    public ArticleService(ArticleRepository articleRepository, UserService userService) {
        this.userService = userService;
        this.articleRepository = articleRepository;
    }

    public void createArticle(Article article) {
        articleRepository.save(article);
    }
    public Optional<Article> getArticleById(Long id) {
        return articleRepository.findById(id);
    }
    public void deleteArticleById(Long id) {
        User user = userService.findUserById(userService.getCurrentUserById());
        System.out.println("USER " + user);
        articleRepository.deleteById(id);
    }
    public void updateArticle(Long id, String articleForm) {
        Article article = articleRepository.findById(id).get();
        article.setTitle(articleForm);
        articleRepository.save(article);
    }

    public List<Article> allArticles(Long userId) {
        System.out.println(articleRepository.findArticlesByAuthor(userService.findUserById(userId)));
        return articleRepository.findArticlesByAuthor(userService.findUserById(userId));
    }
}
