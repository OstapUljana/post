var i = 0;

function loadPosts(data, clas) {
     if (data != null) {
    		for (var j=0; j < data.length; j++)
		        $("." + clas).append(		            
		            		 "<div class='content-section-a'><div class='container'>"+
		     		         "<div class='row' id='row'>" + 
		            		"<hr class='section-heading-spacer'>"+
		                    "<div class='clearfix'></div>"+
		                    "<a href='rest/article/getPagePost/"+data[j].idArticle+
		                    "'><h2 class='section-heading'>" + data[j].title+ 
		                    "</h2></a>"+
		                    "<p class='lead'>"+ data[j].text+ "</p>"+
		                    "</div></div></div>"
        );
    }
}

function five() {
	$.ajax({
	    type: "get",
	    url: "/restwebservice/rest/article/list/"+i+"/"+(i+5)+"/0/datetime",
	    crossDomain: true,
	    dataType: "json",
	    cache: false,
	    success: function (data) {
	    	loadPosts(data, "pack");
	    	i=i+5;
	    }
	});
}

$('#pack').ready(function () {
	five();
	$("#more_post").click(function(){    		
		five();	
		return false;
	});        
});