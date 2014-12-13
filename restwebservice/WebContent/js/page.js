var currentArticle;

$(document).ready(function(){
    var URL = window.location.search;
    var getRequest = URL.split("?")[1];
    id = getRequest.split("=")[1];
    getListPost(id);
    
});

function getListPost(id){
	$.ajax({
    	type:"get",
        dataType:"json",
        url:"rest/article/getList/"+id,
        cache: false,
        success:function(data){
        	allPostUser(data, "allpost");
       }
    });
}

function allPostUser(data, clas) {
    if (data != null) {
       for (var i = 0; i < data.length; i++) {
	       $("." + clas).append(
	    		   "<div class='col-lg-8'>"+
	                	"<h1 id='title'>"+data[i].title+"</h1>"+
	                "<p class='users'>"+
	                    "Posted by <a href='#' id='users'>"+"<a href='rest/article/getPageUser/"
	                    +data[i].users+"'>"+data[i].idUsers+"</a></a>"+
	                "</p>"+
	                "<hr>"+	
	                "<p id='datetime'>"+"<span class='glyphicon glyphicon-time' ></span> Posted on "+ data.datetime+"</p>"+
	                "<hr>"+
	                "<p class='lead' id='text'>"+"<div class='well'>"+data.text+"</div>"+"</p>"+     
	                "<hr></div>"
        		)
        }
    }
}