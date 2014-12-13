package com.restwebservice.rest;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;




import com.restwebservice.entities.*;
import com.restwebservice.json.*;
import com.restwebservice.util.DaoFactory;
@Path("/feedback")
public class FeedbackRest {
	//список фидбеков
	@GET
	@Path("/allfeedbacks")
	@Produces("application/json")
	public ArrayList<FeedbackJson> getAllFeedbacks(){
		ArrayList<FeedbackJson> feedbackJson = new ArrayList<FeedbackJson>();
        try{
        List<com.restwebservice.entities.Feedback> feedbacks = DaoFactory.getFeedbackDaoImplInstance().selectAllFeedbacks();
             
        for (com.restwebservice.entities.Feedback feedback : feedbacks) {
            FeedbackJson feedJson = new FeedbackJson(feedback);
            feedbackJson.add(feedJson);
            System.out.println(feedbackJson.toString());
        }
        }
        catch(NullPointerException e){
        	System.out.println("NullPointerException for feedbacks");
        }
        return feedbackJson;
	}
	
		//отправить фидбек
	 	@POST
	    @Path("/addfeedback")
	    public Response createFeedback(@FormParam("email") String eMail,
	    							@FormParam("text") String text_feedback) {
	 		
	        Feedback feedback = new Feedback();	        
	        feedback.setDate(new Timestamp(new Date().getTime()));
	        feedback.seteMail(eMail);
	        feedback.setText(text_feedback);	
	        
	        DaoFactory.getFeedbackDaoImplInstance().insert(feedback);
	        return Response.ok().build();
	    }
}
