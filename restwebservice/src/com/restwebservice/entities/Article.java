package com.restwebservice.entities;



import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by User on 07.10.2014.
 */
@Entity
public class Article {
    private int idArticle;
    private String title;
    private Timestamp datetime;
    private String text;
    private Users usersByIdUsers;

    @Id
    @Column(name = "idArticle", nullable = false, insertable = true, updatable = true)
    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    @Basic
    @Column(name = "title", nullable = true, insertable = true, updatable = true, length = 255)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "datetime", nullable = true, insertable = true, updatable = true)
    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    @Basic
    @Column(name = "text", nullable = true, insertable = true, updatable = true, length = 65535)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Article article = (Article) o;

        if (idArticle != article.idArticle) return false;
        if (datetime != null ? !datetime.equals(article.datetime) : article.datetime != null) return false;
        if (text != null ? !text.equals(article.text) : article.text != null) return false;
        if (title != null ? !title.equals(article.title) : article.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idArticle;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (datetime != null ? datetime.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
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
