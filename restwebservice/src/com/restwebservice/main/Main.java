package com.restwebservice.main;

import javax.mail.MessagingException;

import com.restwebservice.dao.UsersDaoImpl;
import com.restwebservice.json.UsersJson;
import com.restwebservice.rest.EmailSendDelivery;





/**
 * Created by User on 25.09.2014.
 */
public class Main {
    public static void main(String[] args) throws RuntimeException, MessagingException{
        /*UsersDaoImpl us= new UsersDaoImpl();
        UsersJson uss=new UsersJson(us.selectByEmail("ostapulja@gmail.com"));
        System.out.println(uss.toString());*/
        EmailSendDelivery e = new EmailSendDelivery();
       System.out.println(e.sendDelivery());
    }
}

