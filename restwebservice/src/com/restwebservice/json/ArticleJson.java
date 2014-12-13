package com.restwebservice.json;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import com.restwebservice.entities.Article;


public class ArticleJson {
	private int idArticle;
	private String title;
	private String datetime;
	private String text;
	private String idUsers;
    private String tag;
    DateFormat df = new SimpleDateFormat("dd MMMM yyyy 'at'  HH:mm", Locale.UK);	
    
	public ArticleJson(Article article) {
		super();
		this.idArticle = article.getIdArticle();
		this.title = article.getTitle();
		
		this.datetime = (df.format(article.getDatetime()).toString());	
		this.text = article.getText();
		this.idUsers = article.getUsersByIdUsers().getName();
		this.tag = article.getTag();
	}
	public int getIdArticle() {
		return idArticle;
	}
	public void setIdArticle(int idArticle) {
		this.idArticle = idArticle;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getIdUsers() {
		return idUsers;
	}
	public void setIdUsers(String idUsers) {
		this.idUsers = idUsers;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	
	@Override
	public String toString() {
		return "ArticleJson [idArticle=" + idArticle + ", title=" + title
				+ ", datetime=" + datetime.toString() + ", text=" + text + ", idUsers="
				+ idUsers + ", tag=" + tag + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((datetime == null) ? 0 : datetime.hashCode());
		result = prime * result + idArticle;
		result = prime * result + ((idUsers == null) ? 0 : idUsers.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArticleJson other = (ArticleJson) obj;
		if (datetime == null) {
			if (other.datetime != null)
				return false;
		} else if (!datetime.equals(other.datetime))
			return false;
		if (idArticle != other.idArticle)
			return false;
		if (idUsers == null) {
			if (other.idUsers != null)
				return false;
		} else if (!idUsers.equals(other.idUsers))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	 
}
