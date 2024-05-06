package com.boots.controller;

import java.util.List;
import com.boots.entity.Comment;
import com.boots.service.CommentService;
import com.boots.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class CommentController {
    private final CommentService commentService;
    private final UserService userService;

    public CommentController(CommentService commentService, UserService userService) {
        this.commentService = commentService;
        this.userService = userService;
    }

    @PostMapping("/comment/create")
    public String createComment(@RequestParam("id") Long id, @RequestParam("comment") String commentText, @RequestParam("articleId") Long articleId) {
        // Создание комментария к статье
        commentService.createComment(articleId, commentText, userService.findUserById(userService.getCurrentUserById()), id);
        return "redirect:/article"; // Перенаправляем на страницу со статьями
    }

    @PostMapping("/comment/delete/{id}")
    public String deleteComment(@PathVariable("id") Long commentId) {
        // Удаление комментария по его ID
        commentService.deleteCommentById(commentId);
        return "redirect:/article"; // Перенаправляем на страницу со статьями
    }

    @PostMapping("/comment/update/{id}")
    public String updateComment(@PathVariable("id") Long commentId, @RequestParam("updatedComment") String updatedComment) {
        // Обновление текста комментария
        commentService.updateComment(commentId, updatedComment);
        return "redirect:/article"; // Перенаправляем на страницу со статьями
    }

    @GetMapping("/comments")
    public String getAllComments(Model model) {
        // Получение всех комментариев для отображения
        List<Comment> allComments = commentService.getAllComments();
        model.addAttribute("allComments", allComments);
        return "comments"; // Возвращаем представление для отображения всех комментариев
    }
}