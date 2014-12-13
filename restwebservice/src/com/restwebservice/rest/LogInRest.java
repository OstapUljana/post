package com.restwebservice.rest;

import javax.ws.rs.CookieParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import org.apache.commons.codec.digest.DigestUtils;

import com.restwebservice.dao.UsersDaoImpl;
import com.restwebservice.entities.Users;
import com.restwebservice.json.UsersJson;
import com.restwebservice.util.DaoFactory;
import com.restwebservice.util.MailSending;


/**
 * Created by User on 27.09.2014.
 */
@Path("/session")
public class LogInRest {
    private final String COOKIE_PATH = "/";
    private final String COOKIE_DOMAIN = "";

    // Login
    @POST
    @Path("/log")
    public Response login(@FormParam("email") String email,
            @FormParam("password") String password){
    	
        Users user = DaoFactory. getUsersDaoImplInstance().selectByEmail(email);
        String hexedPassword = DigestUtils.sha1Hex(password);     
        
        // User not exist
        if (user == null) {
            return Response.status(409).entity("Incorrect authorization").build();
        }
        // Wrong user password
        if (!hexedPassword.equals(user.getPassword())) {
            return Response.status(409).entity("Incorrect authorization").build();
        }
        // All OK :
        NewCookie cookie = new NewCookie("user", email, COOKIE_PATH, COOKIE_DOMAIN, "",8800,false);
        // HTTP 307 - Redirect
        return Response.ok()
                .cookie(cookie).build();
    }
    
    // 
    @GET
    @Path("/get-user")
    @Produces("application/json")
    public UsersJson getUser(@CookieParam("user") String userEmail) {
        Users user = DaoFactory.getUsersDaoImplInstance().selectByEmail(userEmail);
        return new UsersJson(user);
    }

    // logout
    @POST
    @Path("/logout")
    public Response logout(@CookieParam("user") String userEmail) {
        if (userEmail == null) {
            return Response.status(400).entity("User not signed in").build();
        }
        NewCookie authorizedCookie =
                new NewCookie("user", userEmail, COOKIE_PATH, COOKIE_DOMAIN, "", 0, false); // Cookie age = 0//

        return Response.ok().cookie(authorizedCookie).build();
    }
    
    @POST
    @Path("/forgotpassword")
    public Response forgotPassword(@FormParam("email") String email){
    	if (DaoFactory.getUsersDaoImplInstance().exist(email)==false) {
              return Response.status(409)
                      .entity("Incorrect email").build();
        }
    	String st = "http://localhost:8080/restwebservice/newpassword.html?id="+email;
    	MailSending mail = new MailSending();	
        mail.sendPassword(email, st);
        
        NewCookie cookie = new NewCookie("forgotpassword", email, COOKIE_PATH, COOKIE_DOMAIN, "",8800,false);

        // HTTP 307 - Redirect
        return Response.ok()
                .cookie(cookie).build();
    }
    
    @POST
    @Path("/newpassword")
    public Response newPassword(@CookieParam("forgotpassword") String userEmail,
    		@FormParam("email") String email,
            @FormParam("password") String password) {
     
        // HTTP 409 (Conflict)
        if (userEmail==email) {
            return Response.status(409)
                    .entity("Incorrect data").build();
        }

        Users user = DaoFactory.getUsersDaoImplInstance().selectByEmail(email);
        user.setPassword(DigestUtils.sha1Hex(password));       

        DaoFactory.getUsersDaoImplInstance().update(user);
        NewCookie cookie =
                new NewCookie("forgotpassword", userEmail, COOKIE_PATH, COOKIE_DOMAIN, "", 0, false); // Cookie age = 0//
        
        return Response.ok()
                .cookie(cookie).build();
    }
}

