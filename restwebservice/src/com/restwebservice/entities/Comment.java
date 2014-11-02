package com.restwebservice.entities;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by User on 02.11.2014.
 */
@Entity
public class Comment {
    private int idcomment;
    private Timestamp date;
    private String description;
    private Article articleByIdArticle;
    private Users usersByIdUsers;

    @Id
    @Column(name = "idcomment", nullable = false, insertable = true, updatable = true)
    public int getIdcomment() {
        return idcomment;
    }

    public void setIdcomment(int idcomment) {
        this.idcomment = idcomment;
    }

    @Basic
    @Column(name = "date", nullable = true, insertable = true, updatable = true)
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Basic
    @Column(name = "description", nullable = true, insertable = true, updatable = true, length = 255)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        if (idcomment != comment.idcomment) return false;
        if (date != null ? !date.equals(comment.date) : comment.date != null) return false;
        if (description != null ? !description.equals(comment.description) : comment.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idcomment;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "idArticle", referencedColumnName = "idArticle")
    public Article getArticleByIdArticle() {
        return articleByIdArticle;
    }

    public void setArticleByIdArticle(Article articleByIdArticle) {
        this.articleByIdArticle = articleByIdArticle;
    }

    @ManyToOne
    @JoinColumn(name = "idUsers", referencedColumnName = "idUsers")
    public Users getUsersByIdUsers() {
        return usersByIdUsers;
    }

    public void setUsersByIdUsers(Users usersByIdUsers) {
        this.usersByIdUsers = usersByIdUsers;
    }
}
