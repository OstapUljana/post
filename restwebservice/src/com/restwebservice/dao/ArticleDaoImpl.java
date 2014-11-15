/**
 * 
 */
package com.restwebservice.dao;

import java.util.List;



import org.hibernate.Query;
import org.hibernate.Session;





import com.restwebservice.entities.Article;
import com.restwebservice.util.HibernateUtil;

/**
 * @author User
 *
 */

public class ArticleDaoImpl {
	
	//Выбор постов из базы по заголовку
	 public List<Article> search(String name, int howMuch){
	        Session session;
	        session = HibernateUtil.getSessionFactory().openSession();
	        Query query = null;
	        query = session.createQuery("FROM Article art WHERE art.title LIKE '%" + name + "%' asc");
	        
	        if (!query.list().isEmpty()) {
	            query.setFirstResult(0);
	            query.setMaxResults(howMuch);
	            List<Article> result = query.list();
	            session.close();
	            return result;
	        } else {
	            session.close();
	            return null;
	        }
	 }
	
	 //Выбор постов из базы по полю byWhat
	 public List<Article> selectOrdered(String byWhat,int howmuch,boolean order){
	        Session session;
	        session = HibernateUtil.getSessionFactory().openSession();
	        Query query = null;
	        if (order == true)
	            query = session.createQuery("FROM Article art order by art." + byWhat + " asc");
	        else
	            query = session.createQuery("FROM Article art order by art." + byWhat + " desc");
	        if (!query.list().isEmpty()) {
	            query.setFirstResult(0);
	            query.setMaxResults(howmuch);
	            List<Article> result = query.list();
	            session.close();
	            return result;
	        } else {
	            session.close();
	            return null;
	        }
	    }
	
	//Занесение поста в БД
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

	 public Article selectById(int id) {
	        Session session;
	        session = HibernateUtil.getSessionFactory().openSession();
	        Query query = session.createQuery("FROM Article art WHERE art.id=" +
	                Integer.toString(id));
	        if (!query.list().isEmpty()) {
	            Article result = (Article) query.list().get(0);
	            session.close();
	            return result;
	        } else {
	            session.close();
	            return null;
	        }
	    }
}
