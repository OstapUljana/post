package com.restwebservice.json;

import java.sql.Timestamp;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import com.restwebservice.entities.Comment;

public class CommentJson {
	private int idcomment;
    private String date;
    private String description;
    private String articleByIdArticle;
    private String usersByIdUsers;
    DateFormat df = new SimpleDateFormat("dd MMMM yyyy 'at'  HH:mm", Locale.UK);	
    
	public CommentJson(Comment comment) {
		super();
		this.idcomment = comment.getIdcomment();
		this.date = (df.format(comment.getDate()).toString());
		this.description = comment.getDescription();
		this.articleByIdArticle = Integer.toString(comment.getArticleByIdArticle().getIdArticle());
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


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
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
