/**
 * 
 */
package com.restwebservice.rest;

import java.util.Date;
import java.sql.Timestamp;

import javax.ws.rs.CookieParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.restwebservice.dao.ArticleDaoImpl;
import com.restwebservice.dao.UsersDaoImpl;
import com.restwebservice.entities.Article;
import com.restwebservice.util.DaoFactory;

/**
 * @author User
 *
 */

@Path("/createpost")
public class CreatePost {
	
	@POST
	public Response createPost(@CookieParam("user") String enteredUser,			
			@FormParam("post1") String title,
			@FormParam("post") String post){
		
		Article article = new Article();
		
		Date date= new Date();
        long time = date.getTime();
        Timestamp ts = new Timestamp(time);
	  
		article.setText(post);
		article.setTitle(title);
		System.out.println("Asdfadkj");
		System.out.println(title);
		article.setDatetime(ts);
		article.setUsersByIdUsers(DaoFactory.getUsersDaoImplInstance().selectByEmail(enteredUser));
		
		DaoFactory.getArticleDaoImplInstance().insert(article);
		
		return Response.ok().build();
	}

}
