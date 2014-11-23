package com.restwebservice.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.CookieParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import java.sql.Timestamp;

import com.restwebservice.entities.*;
import com.restwebservice.json.*;
import com.restwebservice.util.DaoFactory;

@Path("/comment")
public class Comment {
	
	
    @GET
    @Path("/list/{idArticle}")
    @Produces("application/json")
    public ArrayList<CommentJson> getAllComment(@PathParam("idArticle") int idArticle) {

        ArrayList<CommentJson> commentsJson = new ArrayList<CommentJson>();
        
        List<com.restwebservice.entities.Comment> comments = DaoFactory.getCommentDaoImplInstance().selectByArticle(
        		DaoFactory.getArticleDaoImplInstance().selectById(idArticle));
        
        
        for (com.restwebservice.entities.Comment comment : comments) {
            CommentJson commentJson = new CommentJson(comment);
            commentsJson.add(commentJson);
            System.out.println(commentsJson.toString());
        }

        return commentsJson;
    }
	
	
	
    @POST
    @Path("/addcomment")
    public Response createComment(@CookieParam("user") String userEmail,
    		@FormParam("article") int idArticle,
    		@FormParam("comment") String text_comment) {
    	
		System.out.println("article "+idArticle+" comment " +text_comment);

       // System.out.println(user.toString()+article.toString());

        com.restwebservice.entities.Comment comment = new com.restwebservice.entities.Comment();
        comment.setDate(new Timestamp(new Date().getTime()));
        comment.setArticleByIdArticle(DaoFactory.getArticleDaoImplInstance().selectById(idArticle));
        comment.setUsersByIdUsers(DaoFactory.getUsersDaoImplInstance().selectByEmail(userEmail));
        comment.setDescription(text_comment);
        
        DaoFactory.getCommentDaoImplInstance().insert(comment);
        
        return Response.ok().build();
    }
}
