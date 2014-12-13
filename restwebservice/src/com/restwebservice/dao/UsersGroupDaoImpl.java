package com.restwebservice.dao;

import org.hibernate.Query;
import org.hibernate.Session;

import com.restwebservice.entities.Usersgroup;
import com.restwebservice.util.HibernateUtil;

/**
 * Created by User on 26.09.2014.
 */
public class UsersGroupDaoImpl {

    public Usersgroup getGroupByType(String type) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("FROM Usersgroup where type='" + type + "'");
        Usersgroup result = (Usersgroup) query.list().get(0);
        session.close();
        return result;
    }
}
