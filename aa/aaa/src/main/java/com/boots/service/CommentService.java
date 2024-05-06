package com.boots.service;

import com.boots.entity.Comment;
import com.boots.entity.User;
import com.boots.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final ArticleService articleService;

    public CommentService(CommentRepository commentRepository, UserService userService, ArticleService articleService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.articleService = articleService;
    }

    public void createComment(Long articleId, String commentText, User user, Long Id) {
        Comment comment = new Comment();
        comment.setId(Id);
        comment.setArticle(articleService.getArticleById(articleId).get());
        comment.setUser(user);
        comment.setText(commentText);
        commentRepository.save(comment);
    }

    public Optional<Comment> getCommentById(Long id) {
        return commentRepository.findById(id);
    }

    public void deleteCommentById(Long id) {
        commentRepository.deleteById(id);
    }

    public void updateComment(Long id, String updatedComment) {
        Comment comment = commentRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Comment not found"));
        comment.setText(updatedComment);
        commentRepository.save(comment);
    }

    public List<Comment> getAllComments() {
        Long userId = userService.getCurrentUserById();
        return commentRepository.findCommentsByUserId(userId);
    }
}