package com.restwebservice.util;

import com.restwebservice.dao.*;


public class DaoFactory {
    
    private static UsersDaoImpl userDao = new UsersDaoImpl();
    private static ArticleDaoImpl articleDao = new ArticleDaoImpl();
    private static UsersGroupDaoImpl usersGroupDao = new UsersGroupDaoImpl();
    private static CommentDaoImpl commentDao = new CommentDaoImpl();
    
    public static UsersDaoImpl getUsersDaoImplInstance(){
        return userDao;
    }
    
    public static ArticleDaoImpl getArticleDaoImplInstance(){
        return articleDao;
    }
    
    public static UsersGroupDaoImpl getUsersGroupDaoImplInstance(){
        return usersGroupDao;
    }

    public static CommentDaoImpl getCommentDaoImplInstance(){
        return commentDao;
    }
    
    private DaoFactory() {

    }
}
