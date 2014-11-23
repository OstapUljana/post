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
            $("#users").html(data.idUsers);
            $("#datetime").html("<span class='glyphicon glyphicon-time' ></span> Posted on "+ data.datetime);
            $("#title").html(data.title);
            $("#text").html("<div class='well'>"+data.text+"</div>");
       }
    });
  
  getComments(id);

});


$('#bs-example-navbar-collapse-1').ready(function () {
    // If user is Sign In show logout
    $.ajax({
        type: 'get',
        url: '/restwebservice/rest/session/get-user',
        crossDomain: true,
        success: function (data) {
            if (data.name == null) { // if response doesn't have user
                loginButtonEnable();
                sendCommentsDisable();
            } else {
                logoutButtonEnable(data.name);
                sendCommentsEnabe();
/*------------------------------------------------------------------*/
                $("#form_comment").submit(function(e){
                    e.preventDefault();
                    var comment = $("#text_comment").val();
                    addComment(id,comment);
                });
            }
        }
    });

});

function logoutButtonEnable(name) {
    $('#bs-example-navbar-collapse-1').html(
            "<ul class='nav navbar-nav navbar-right'>"+
                "<li> <a href='#'>About</a> </li>"+
                "<li> <a href='#'>Feedback</a> </li>"+
                "<li> <a href='javascript:void(0)' id = 'logout_button'>Log out</a></li>"+
                "<li> <a href='#'>" + name +"</a></li>"+
            "</ul>");

    // set on click action on logout button
    $('#logout_button').click(function () {
        $.ajax({
            type: 'post',
            url: '/restwebservice/rest/session/logout',
            crossDomain: true,
            success: function (data) {
                location.reload();
            }
        });
    });
}


function loginButtonEnable() {
    $(('#bs-example-navbar-collapse-1')).html(
    		"<ul class='nav navbar-nav navbar-right'>"+
            "<li> <a href='#'>About</a> </li>"+
            "<li> <a href='#'>Feedback</a> </li>"+
            "<li> <a href='login.html'>Log in</a></li>"+
        "</ul>");
    
    $("#enter").click(function () {
        $("#login").slideToggle("slow");
        $(this).toggleClass("active");
    });
}

//----------------------------------

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
                		"<div class=media'>"+
		                    "<div class='media-body'>"+
		                        "<h4 class='media-heading'>"+data[i].usersByIdUsers+
		                            "<small>  <span class='glyphicon glyphicon-time' ></span> Commented on"+data[i].date+"</small>"+
		                        "</h4>"+data[i].description+
		                     "</div>"+
		                "</div>")

            }
        }
    });
}

        
        
        
