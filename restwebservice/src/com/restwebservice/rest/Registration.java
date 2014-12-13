package com.restwebservice.rest;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.apache.commons.codec.digest.DigestUtils;

import com.restwebservice.dao.UsersDaoImpl;
import com.restwebservice.dao.UsersGroupDaoImpl;
import com.restwebservice.entities.Users;
import com.restwebservice.util.DaoFactory;
import com.restwebservice.util.MailSending;

@Path("/reg")
public class Registration {
	
    @POST
    public Response register(@FormParam("email") String email,
    		@FormParam("name") String name,
            @FormParam("password") String password) {
     
        // HTTP 409 (Conflict)
        if (DaoFactory.getUsersDaoImplInstance().exist(email)) {
            return Response.status(409)
                    .entity("User with this name is exist.").build();
        }

        Users user = new Users();

        user.setName(name);
        user.seteMail(email);
        user.setPassword(DigestUtils.sha1Hex(password));
        user.setUsersgroupByUsersGroup(DaoFactory.getUsersGroupDaoImplInstance().getGroupByType("user"));

        DaoFactory.getUsersDaoImplInstance().insert(user);
        MailSending mail = new MailSending();	
        mail.sendCongratulations(email);
        return Response.ok().build();
    }
}
