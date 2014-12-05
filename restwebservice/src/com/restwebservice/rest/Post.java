package com.restwebservice.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import com.restwebservice.dao.ArticleDaoImpl;
import com.restwebservice.entities.Article;
import com.restwebservice.json.*;
import com.restwebservice.util.DaoFactory;

import javax.ws.rs.CookieParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

@Path("/article")
public class Post {
	
	@Path("list/{limit}/{order}/{byWhat}")
    @GET
    @Produces("application/json")
    public ArrayList<ArticleJson> GetLastAddedBooks(@PathParam("limit") String limit,
    		@PathParam("order") String order, @PathParam("byWhat") String byWhat) {

        ArrayList<ArticleJson> artsJson = new ArrayList<ArticleJson>();
        
        List<Article> articles = DaoFactory.getArticleDaoImplInstance().selectOrdered(byWhat, Integer.parseInt(limit), 
        		Integer.parseInt(order) == 0 ? false : true);
        try{
        for (Article article : articles) {
            ArticleJson artJson = new ArticleJson(article);
            artsJson.add(artJson);
            System.out.println(artJson.toString());
        }
        }
        catch(NullPointerException e){
        	System.out.println("NullPointerException --> No articles");
        }
        return artsJson;
    }
	
	@Path("getPagePost/{idArticle}")
    @GET
    public Response getSinglePage(@PathParam("idArticle") String id){
        URI location;
        try {
            location = new URI("../post.html?id="+id);
        }
        catch(URISyntaxException e){
            return null;
        }
        return Response.temporaryRedirect(location).build();
    }
	
	@Path("get/{id}")
    @GET
    @Produces("application/json")
    public ArticleJson getBook(@PathParam("id") String id){
        return new ArticleJson(DaoFactory.getArticleDaoImplInstance().selectById(Integer.parseInt(id)));
    }
	
	
    @POST
    @Path("/deletepost")
    //@Produces("application/json")
    public Response deleteArticle(@CookieParam ("user") String email,
    		@FormParam("article") int idArticle) {
		System.out.println(idArticle+"------------------------------------------------------------");
		Article article =(DaoFactory.getArticleDaoImplInstance().selectById(idArticle));
		DaoFactory.getCommentDaoImplInstance().deleteComments(article);
		DaoFactory.getArticleDaoImplInstance().delete(article);

		return Response.status(307).entity("index1.html").build();
    }
	
}
