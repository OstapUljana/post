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

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;

import com.restwebservice.entities.*;
import com.restwebservice.json.*;
import com.restwebservice.util.DaoFactory;

@Path("/comment")
public class CommentRest {	
	//все комментарии
    @GET
    @Path("/list/{idArticle}")
    @Produces("application/json")
    public ArrayList<CommentJson> getAllComment(@PathParam("idArticle") int idArticle) {
        ArrayList<CommentJson> commentsJson = new ArrayList<CommentJson>();
        try{
        List<com.restwebservice.entities.Comment> comments = DaoFactory.getCommentDaoImplInstance().selectByArticle(
        		DaoFactory.getArticleDaoImplInstance().selectById(idArticle));
                
        for (com.restwebservice.entities.Comment comment : comments) {
            CommentJson commentJson = new CommentJson(comment);
            commentsJson.add(commentJson);
            System.out.println(commentsJson.toString());
        }
        }
        catch(NullPointerException e){
        	System.out.println("NullPointerException for comments");
        }
        return commentsJson;
    }
	//создать комментарий	
    @POST
    @Path("/addcomment")
    public Response createComment(@CookieParam("user") String userEmail,
    		@FormParam("article") int idArticle,
    		@FormParam("comment") String text_comment) {    	
		//System.out.println("article "+idArticle+" comment " +text_comment);

        com.restwebservice.entities.Comment comment = new com.restwebservice.entities.Comment();
        comment.setDate(new Timestamp(new Date().getTime()));
        comment.setArticleByIdArticle(DaoFactory.getArticleDaoImplInstance().selectById(idArticle));
        comment.setUsersByIdUsers(DaoFactory.getUsersDaoImplInstance().selectByEmail(userEmail));
        comment.setDescription(text_comment);
        
        DaoFactory.getCommentDaoImplInstance().insert(comment);        
        return Response.ok().build();
    }

   //цдаление комментария
    @Path("/deletecomment/{idComment}/{idArticle}")
    @GET
    //@Produces("application/json")
    public Response deleteComment(@CookieParam ("user") String email,
    		@PathParam ("idComment") String idComment,
    		@PathParam("idArticle") String idArticle) {
		com.restwebservice.entities.Comment comment =(DaoFactory.getCommentDaoImplInstance().selectByIdComment(
				Integer.parseInt(idComment)));
		/*if (comment.getUsersByIdUsers().equals(DaoFactory.getUsersDaoImplInstance().selectByEmail(email))){
			DaoFactory.getCommentDaoImplInstance().deleteCommentById(comment);
		return Response.ok().build();
		}
		else{
			return Response.status(409)
                    .entity("NO").build();
		}*/
		URI location;
        try {
        	if (comment.getUsersByIdUsers().getIdUsers()==DaoFactory.getUsersDaoImplInstance().selectByEmail(email).getIdUsers()){
        		System.out.println(comment.getUsersByIdUsers().getIdUsers()+" "+DaoFactory.getUsersDaoImplInstance().selectByEmail(email).getIdUsers());
    			DaoFactory.getCommentDaoImplInstance().deleteCommentById(comment);
    		}
    		else{
    			return Response.status(409)
                        .entity("NO").build();
    		}
            location = new URI("../post.html?id="+idArticle);
        }
        catch(URISyntaxException e){
            return null;
        }
        return Response.temporaryRedirect(location).build();		
    }
}
