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
            $("#text").html(data.text);
       }

    });
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
            } else {
                logoutButtonEnable(data.name);
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