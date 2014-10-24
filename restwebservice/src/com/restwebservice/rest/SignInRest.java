package com.restwebservice.rest;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import com.restwebservice.dao.UsersDaoImpl;
import com.restwebservice.entities.Users;


/**
 * Created by User on 27.09.2014.
 */
@Path("/log")
public class SignInRest {
    private final String COOKIE_PATH = "/";
    private final String COOKIE_DOMAIN = "";

    @POST
   // @Produces("text/plain")
    public Response login(@FormParam("email") String email,
            @FormParam("password") String password){
    	
    	System.out.print(email+" "+password);
        UsersDaoImpl usersDao = new UsersDaoImpl();
        Users user = usersDao.selectByEmail(email);
        
        System.out.print(email+" "+password);
        
        // User not exist
        if (user == null) {
            return Response.status(400).entity("Error1").build();
        }

        // Wrong user password
        if (!user.getPassword().equals(password)) {
            return Response.status(400).entity("Error2").build();
        }

        // All OK :
        NewCookie cookie = new NewCookie("user", email, COOKIE_PATH, COOKIE_DOMAIN, "",8800,false);

        // HTTP 307 - Redirect
        return Response.status(307).entity("index.html")
                .cookie(cookie).build()
                ;
    }
}

