package com.restwebservice.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;





import com.restwebservice.entities.Article;
import com.restwebservice.entities.Users;
import com.restwebservice.util.HibernateUtil;

import javax.persistence.EntityManagerFactory;


/**
 * Created by User on 25.09.2014.
 */
public class UsersDaoImpl {

    public Users selectByEmail(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery(" from Users user where user.eMail='" +
                email + "'");
        if (!query.list().isEmpty()) {
            Users result = (Users) query.list().get(0);
            session.close();
            return result;
        } else {
        	System.out.println("No email");
            session.close();
            return null;
        }
    }
    
    public Users selectById(int id) {
    	Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery(" from Users user where user.idUsers=" +id);
        if (!query.list().isEmpty()) {
            Users result = (Users) query.list().get(0);
            session.close();
            return result;
        } else {
        	System.out.println("No id");
            session.close();
            return null;
        }
    }
    
    public List<Users> selectAllUsers(){
    	 Session session;
	        session = HibernateUtil.getSessionFactory().openSession();
	        Query query = null;
	        query = session.createQuery("FROM Users");
	        if (!query.list().isEmpty()) {
            List<Users> result = query.list();
            session.close();
            return result;
        } else {
            session.close();
            return null;
        }
    }

    public boolean exist(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery(" from Users user where user.eMail='" + 
        		email + "'");
        if (!query.list().isEmpty()) {
            session.close();
            return true;
        } else {
            session.close();
            return false;
        }
    }
    
    public void insert(Users user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.persist("Users", user);
            if (!session.getTransaction().wasCommitted())
                session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null)
                session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }
    
    
    public void update(Users user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.update(user);
            if (!session.getTransaction().wasCommitted())
                session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null) session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }
}
