package com.restwebservice.dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.restwebservice.entities.Feedback;
import com.restwebservice.util.HibernateUtil;

public class FeedbackDaoImpl {
	//выбираем все фидбеки
	 public List<Feedback> selectAllFeedbacks(){
    	 Session session;
	        session = HibernateUtil.getSessionFactory().openSession();
	        Query query = null;
	        query = session.createQuery("FROM Feedback");
	        if (!query.list().isEmpty()) {
            List<Feedback> result = query.list();
            session.close();
            return result;
        } else {
            session.close();
            return null;
        }
    }
	 
	 //выбираем все филбеки по имейлу
	 public List<Feedback> selectByeMail(String eMail) {
	        Session session;
	        session = HibernateUtil.getSessionFactory().openSession();
	        Query query = session.createQuery(" FROM Feedback feed WHERE feed.eMail='" 
	        + eMail + "'");
	        if (!query.list().isEmpty()) {
	        	List<Feedback> result = query.list();
	            session.close();            
	            return result;
	        } else {
	            session.close();
	            return null;
	        }
	    }
	 
	//уаляем фидбек по айди
	 public void deleteFeedbackById(Feedback feedback) {
	        Session session = HibernateUtil.getSessionFactory().openSession();
	        System.out.println("-----------------------------"+feedback.getIdfeedback());
	        Transaction tx = null;
	        try {
	        	tx = session.beginTransaction();
	        	Query query = session.createQuery("delete from Feedback feedback where feedback.idfeedback = '" +
	        										feedback.getIdfeedback() + "'");
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
	 
	//вставка 
	public void insert(Feedback feedback) {
		System.out.println(feedback.geteMail() + "  " + feedback.getText());
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
        	System.out.println("dfndfnv;ldfk");
            session.beginTransaction();
            session.persist("Feedback", feedback);
            if (!session.getTransaction().wasCommitted())
                session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null)
                session.getTransaction().rollback();
        } finally {
            session.close();
        }
        
    }    
	
    //обновление
    public void update(Feedback feedback) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.update(feedback);
            if (!session.getTransaction().wasCommitted())
                session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null) session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }
}
