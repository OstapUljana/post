package com.restwebservice.util;

import com.restwebservice.dao.*;


public class DaoFactory {
    
    private static UsersDaoImpl userDao = new UsersDaoImpl();
    private static ArticleDaoImpl articleDao = new ArticleDaoImpl();
    private static UsersGroupDaoImpl usersGroupDao = new UsersGroupDaoImpl();
    
    public static UsersDaoImpl getUsersDaoImplInstance(){
        return userDao;
    }
    
    public static ArticleDaoImpl getArticleDaoImplInstance(){
        return articleDao;
    }
    
    public static UsersGroupDaoImpl getUsersGroupDaoImplInstance(){
        return usersGroupDao;
    }

    
    private DaoFactory() {

    }
}
