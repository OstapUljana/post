package com.restwebservice.rest;

import java.util.List;

import javax.mail.MessagingException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.restwebservice.util.DaoFactory;
import com.restwebservice.util.MailSending;

@Path("/senddelivery")			//класс для емейл рассылки
public class EmailSendDelivery {
	
@POST
public Response sendDelivery() throws MessagingException{
	List<com.restwebservice.entities.Users> users = DaoFactory.getUsersDaoImplInstance().selectAllUsers();
    String[] emailAdress = new String[users.size()];
    int i = 0;
    for (com.restwebservice.entities.Users uss : users) {
    		emailAdress[i] = uss.geteMail();
    		i++;
    }    
    MailSending mail = new MailSending();	
    mail.sendMonthDelivery(emailAdress);
    return Response.ok().build();
}
	
}
