package com.restwebservice.json;

import java.sql.Timestamp;


import com.restwebservice.entities.Comment;

public class CommentJson {
	private int idcomment;
    private Timestamp date;
    private String description;
    private String articleByIdArticle;
    private String usersByIdUsers;
    
    
	public CommentJson(Comment comment) {
		super();
		this.idcomment = comment.getIdcomment();
		this.date = comment.getDate();
		this.description = comment.getDescription();
		this.articleByIdArticle = comment.getArticleByIdArticle().getTitle();
		this.usersByIdUsers = comment.getUsersByIdUsers().getName();
	}


	@Override
	public String toString() {
		return "CommentJson [idcomment=" + idcomment + ", date=" + date
				+ ", description=" + description + ", articleByIdArticle="
				+ articleByIdArticle + ", usersByIdUsers=" + usersByIdUsers
				+ "]";
	}


	public int getIdcomment() {
		return idcomment;
	}


	public void setIdcomment(int idcomment) {
		this.idcomment = idcomment;
	}


	public Timestamp getDate() {
		return date;
	}


	public void setDate(Timestamp date) {
		this.date = date;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getArticleByIdArticle() {
		return articleByIdArticle;
	}


	public void setArticleByIdArticle(String articleByIdArticle) {
		this.articleByIdArticle = articleByIdArticle;
	}


	public String getUsersByIdUsers() {
		return usersByIdUsers;
	}


	public void setUsersByIdUsers(String usersByIdUsers) {
		this.usersByIdUsers = usersByIdUsers;
	}
    
    
	
	    
	    
	    
}
