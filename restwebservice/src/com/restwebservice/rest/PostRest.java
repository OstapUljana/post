package com.restwebservice.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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
public class PostRest {
		
	//Выбор нескольких статей
	@Path("list/{from}/{to}/{order}/{byWhat}")
    @GET
    @Produces("application/json")
    public ArrayList<ArticleJson> getTryArticles(@PathParam("from") int from,
    		@PathParam("to") int to,	@PathParam("order") String order, 
    		@PathParam("byWhat") String byWhat) {
		System.out.println(from+" "+to+"----------------");  
        ArrayList<ArticleJson> artsJson = new ArrayList<ArticleJson>();
        //selectFromTo(String byWhat,int from, int to, boolean order)
        List<Article> articles = DaoFactory.getArticleDaoImplInstance().selectFromTo(byWhat,from, 
        		to, Integer.parseInt(order) == 0 ? false : true);
        try{
	        for (Article article : articles) {
        	//for(int i=from; i<=to; i++ ){
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
	
	@Path("getPageUser/{idUser}")
    @GET
    public Response getPageUser(@PathParam("idUser") String id){
        URI location;
        try {
            location = new URI("../page.html?id="+id);
        }
        catch(URISyntaxException e){
            return null;
        }
        return Response.temporaryRedirect(location).build();
    }
	
	//статья по айди
	@Path("get/{id}")
    @GET
    @Produces("application/json")
    public ArticleJson getArticle(@PathParam("id") String id){
        return new ArticleJson(DaoFactory.getArticleDaoImplInstance().selectById(Integer.parseInt(id)));
    }
	
	//создание статьи
	@Path("/createpost")
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

	@Path("getList/{id}")
    @GET
    @Produces("application/json")
    public ArrayList<ArticleJson> getAllPostUser(@PathParam("id") int id) {
		
        ArrayList<ArticleJson> artsJson = new ArrayList<ArticleJson>();
        
        List<Article> articles = DaoFactory.getArticleDaoImplInstance().getAllPostUser(
        		DaoFactory.getUsersDaoImplInstance().selectById(id));
        try{
	        for (Article article : articles) {
        	//for(int i=from; i<=to; i++ ){
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
	
	//удаление статьи
    @POST
    @Path("/deletepost")
    //@Produces("application/json")
    public Response deleteArticle(@CookieParam ("user") String email,
    		@FormParam("article") int idArticle) {
		System.out.println(idArticle+"------------------------------------------------------------");
		Article article =(DaoFactory.getArticleDaoImplInstance().selectById(idArticle));
		DaoFactory.getCommentDaoImplInstance().deleteComments(article);
		DaoFactory.getArticleDaoImplInstance().delete(article);

		return Response.ok().build();
    }
	
}
