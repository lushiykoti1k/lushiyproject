package com.boots.repository;

import com.boots.entity.Article;
import com.boots.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    Optional<Article> findById(Long id);

    List<Article> findArticlesByAuthor(User user);
}
