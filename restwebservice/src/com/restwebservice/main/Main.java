package com.restwebservice.main;

import com.restwebservice.dao.UsersDaoImpl;
import com.restwebservice.json.UsersJson;





/**
 * Created by User on 25.09.2014.
 */
public class Main {
    public static void main(String[] args){
        UsersDaoImpl us= new UsersDaoImpl();
        UsersJson uss=new UsersJson(us.selectByEmail("ostapulja@gmail.com"));
        System.out.println(uss.toString());
    }
}

