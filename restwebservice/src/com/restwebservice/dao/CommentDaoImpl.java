package com.restwebservice.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

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
}
