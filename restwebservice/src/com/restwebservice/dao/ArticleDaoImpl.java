/**
 * 
 */
package com.restwebservice.dao;

import org.hibernate.Session;

import com.restwebservice.entities.Article;
import com.restwebservice.util.HibernateUtil;

/**
 * @author User
 *
 */

public class ArticleDaoImpl {
	
	 public void insert(Article article) {
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        try {
	            session.beginTransaction();
	            session.persist("Article", article);
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
