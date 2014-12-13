var currentArticle;

$(document).ready(function(){
    var URL = window.location.search;
    var getRequest=URL.split("?")[1];
    id=getRequest.split("=")[1];
    $.ajax({
    	type:"get",
        dataType:"json",
        url:"rest/article/get/"+id,
        success:function(data){
            currentArticle = data;
            $("#users").html("<a href='rest/article/getPageUser/"+data.users+"'>"+data.idUsers+"</a>");
            $("#datetime").html("<span class='glyphicon glyphicon-time' ></span> Posted on "+ data.datetime);
            $("#title").html(data.title);
            $("#text").html("<div class='well'>"+data.text+"</div>");
       }
    });
  
  getComments(id);
  deletePost(id);
//deleteComment();
  
});

$('#send_comment_div').ready(function () {
    $.ajax({
        type: 'get',
        url: '/restwebservice/rest/session/get-user',
        crossDomain: true,
        success: function (data) {
            if (data.name == null) { // if response doesn't have user
                sendCommentsDisable();
            } else {
                sendCommentsEnabe();
              //  deletePost(id);
              //  deleteComment();
                $("#form_comment").submit(function(e){
                    e.preventDefault();
                    var comment = $("#text_comment").val();
                    addComment(id,comment);
                });
            }
        }
    });

});

function sendCommentsEnabe() {
    $('#send_comment_div').html(
            "<div class='well'>"+
                    "<h4>Leave a Comment:</h4>"+
                    "<form role='form' id='form_comment' method='post'>"+
                        "<div class='form-group'>"+
                            "<textarea class='form-control' id='text_comment' rows='3'></textarea>"+
                        "</div>"+
                        "<button type='submit' class='btn btn-primary'>Submit</button>"+
                    "</form>"+
                "</div>");
}

function sendCommentsDisable() {
    $('#send_comment_div').html("");
}

function addComment(id,comment) {
	$.ajax({
        type: 'post',
        url: '/restwebservice/rest/comment/addcomment',
        crossDomain: true,
        data: {'article': id, 'comment': comment},
        response: 'text', // response type
        success: function (data) {
	        location.reload();
	    },
	    error: function (data) {
	        alert(data);
	    }
    });
}

function getComments(id) {
    $.ajax({
        type: "get",
        dataType: "json",
        url: "rest/comment/list/" + id,
        cache: false,
        success: function (data) {
            for (var i = 0; i < data.length; i++) {
                $(".comments").append(
                		"<hr><div class=media'>"+
		                    "<div class='media-body'>"+
		                        "<h4 class='media-heading'>"+data[i].usersByIdUsers+
		                            " <small>  <span class='glyphicon glyphicon-time' ></span> Commented on "+data[i].date+"</small>"+
		                            "<form  id='delete_post' method='get'>"+
		                            	"<a href='rest/comment/deletecomment/"+data[i].idcomment+"/"+
		                            			data[i].articleByIdArticle+"'>"
		                            +"<div id='delete_comment'></div>"+"</a></form>"+
		                        "</h4>"+data[i].description+
		                     "</div>"+
		                "</div>")

            }
        }
    });
}

function deleteComment(){
	 $('#delete_comment').html(
           "delete");
}      
        
function deletePost(id){
	$.ajax({
        type: 'get',
        url: '/restwebservice/rest/session/get-user',
        crossDomain: true,
        success:function(data){
        	if(data.id == currentArticle.users){
        		$('#delete_post_div').html(
        	            "<hr><form  id='delete_post' method='post'>"+
        	            "<a href='javascript:void(0)' id = 'delete_post'>delete</a>"+
        	            "</form>"
        	            );	
        	}            
        }});
	 
	
	$("#delete_post").click(function(e){
		$.ajax({
	        type: 'post',
	        url: 'rest/article/deletepost',
	        crossDomain: true,
	        cache: false,
	        data: {'article': id},
	        response: 'text',
	        error: function (data) {
	        	//alert(data.responseText);
	        },
	        success: function (data) {          
               document.location.href = '/restwebservice/index.html';
             
           }
	    });
	});
}      
