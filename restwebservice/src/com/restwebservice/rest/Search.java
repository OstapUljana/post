package com.restwebservice.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.CookieParam;
import javax.ws.rs.FormParam;

@Path("/search")
public class Search {
	@Path("/bytitle")
	@GET	
	public Response getArticleByTitle(@FormParam("liu") String title){
		return null;
		
	}
}
