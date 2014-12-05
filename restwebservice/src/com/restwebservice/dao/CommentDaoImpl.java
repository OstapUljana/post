package com.restwebservice.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.restwebservice.entities.*;
import com.restwebservice.util.HibernateUtil;

public class CommentDaoImpl {
	
	public List<Comment> selectByArticle(Article article) {
        Session session;
        session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("FROM Comment com WHERE com.articleByIdArticle=" +
                Integer.toString(article.getIdArticle()));
        if (!query.list().isEmpty()) {
        	List<Comment> result = query.list();
            session.close();            
            return result;
        } else {
            session.close();
            return null;
        }
    }
	
	public Comment selectByIdComment(int idComment) {
        Session session;
        session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("FROM Comment com WHERE com.idcomment=" +
        		Integer.toString(idComment));
        if (!query.list().isEmpty()) {
            Comment result = (Comment) query.list().get(0);
            session.close();
            return result;
        } else {
            session.close();
            return null;
        }
    }

	public void insert(Comment comment) {
	      Session session = HibernateUtil.getSessionFactory().openSession();
	        try {
	            session.beginTransaction();
	            session.persist("Comment", comment);
	            if (!session.getTransaction().wasCommitted())
	                session.getTransaction().commit();
	        } catch (Exception e) {
	            if (session != null)
	                session.getTransaction().rollback();
	        } finally {
	            session.close();
	        }
	}
	public void deleteComments(Article article) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
        	tx = session.beginTransaction();
        	Query query = session.createQuery("delete from Comment comment where comment.articleByIdArticle = '" +
        										article.getIdArticle() + "'");
        	int deletedCount = query.executeUpdate();
            if (!session.getTransaction().wasCommitted())
                session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null)
                session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }
	public void deleteCommentById(Comment comment) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        System.out.println("-----------------------------"+comment.getIdcomment());
        Transaction tx = null;
        try {
        	tx = session.beginTransaction();
        	Query query = session.createQuery("delete from Comment comment where comment.idcomment = '" +
        										comment.getIdcomment() + "'");
        	int deletedCount = query.executeUpdate();
            if (!session.getTransaction().wasCommitted())
                session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null)
                session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }
}
