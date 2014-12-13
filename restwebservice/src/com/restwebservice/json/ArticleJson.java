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
	private int users;
	private String idUsers;
    private String tag;
    DateFormat df = new SimpleDateFormat("dd MMMM yyyy 'at'  HH:mm", Locale.UK);	
    
	public ArticleJson(Article article) {
		super();
		this.idArticle = article.getIdArticle();
		this.title = article.getTitle();
		
		this.datetime = (df.format(article.getDatetime()).toString());	
		this.text = article.getText();
		this.users = article.getUsersByIdUsers().getIdUsers();
		this.idUsers = article.getUsersByIdUsers().getName();
		this.tag = article.getTag();
	}
	
	
	public int getUsers() {
		return users;
	}
	public void setUsers(int users) {
		this.users = users;
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
				+ ", datetime=" + datetime + ", text=" + text + ", users="
				+ users + ", idUsers=" + idUsers + ", tag=" + tag + ", df="
				+ df + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((datetime == null) ? 0 : datetime.hashCode());
		result = prime * result + ((df == null) ? 0 : df.hashCode());
		result = prime * result + idArticle;
		result = prime * result + ((idUsers == null) ? 0 : idUsers.hashCode());
		result = prime * result + ((tag == null) ? 0 : tag.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + users;
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
		if (df == null) {
			if (other.df != null)
				return false;
		} else if (!df.equals(other.df))
			return false;
		if (idArticle != other.idArticle)
			return false;
		if (idUsers == null) {
			if (other.idUsers != null)
				return false;
		} else if (!idUsers.equals(other.idUsers))
			return false;
		if (tag == null) {
			if (other.tag != null)
				return false;
		} else if (!tag.equals(other.tag))
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
		if (users != other.users)
			return false;
		return true;
	}
	
	
}
