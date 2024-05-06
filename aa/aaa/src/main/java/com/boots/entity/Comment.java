package com.boots.entity;

import javax.persistence.*;

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text")
    private String text;

   @ManyToOne

    private Article article;

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne
    private User user;

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }


}