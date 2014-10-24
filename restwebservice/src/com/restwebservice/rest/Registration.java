package com.restwebservice.rest;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.restwebservice.dao.UsersDaoImpl;
import com.restwebservice.dao.UsersGroupDaoImpl;
import com.restwebservice.entities.Users;


@Path("reg")
public class Registration {
	
    @POST
    public Response register(@FormParam("email") String email,
    		@FormParam("name") String name,
                              @FormParam("password") String password
                              ) {

        UsersDaoImpl userDao = new UsersDaoImpl();

       
      
        // HTTP 409 (Conflict)
        if (userDao.exist(email)) {
            return Response.status(409)
                    .entity("Користувач з таким іменем уже зареєстрований").build();
        }

        Users user = new Users();
        UsersGroupDaoImpl group = new UsersGroupDaoImpl();

        user.setName(name);
        user.seteMail(email);
        user.setPassword(password);
        user.setUsersgroupByUsersGroup(group.getGroupByType("user"));

       

        userDao.insert(user);

        return Response.ok().build();
    }
}
