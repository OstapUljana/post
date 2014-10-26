package com.restwebservice.rest;

import java.util.ArrayList;
import java.util.List;

import com.restwebservice.dao.ArticleDaoImpl;
import com.restwebservice.entities.Article;
import com.restwebservice.json.*;
import com.restwebservice.util.DaoFactory;

import javax.ws.rs.GET;
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

        for (Article article : articles) {
            ArticleJson artJson = new ArticleJson(article);
            artsJson.add(artJson);
        }

        return artsJson;
    }

}
