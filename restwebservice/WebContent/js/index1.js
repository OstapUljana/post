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

function allPost(data, clas) {
    var i = 0;
    if (data != null) {
        $("." + clas).each(function () {
            $(this).html(
            		 "<div class='content-section-a'><div class='container'>"+
     		         "<div class='row' id='row'>" + 
            		"<hr class='section-heading-spacer'>"+
                    "<div class='clearfix'></div>"+
                    "<h2 class='section-heading'>" + data[i].title+ "</h2>"+
                    "<p class='lead'>"+ data[i].text+ "</p>"+
                    "</div></div></div>");
            i++
        });
    }
}
    

    $('.pack').ready(function () {
        $.ajax({
            type: "get",
            url: "/restwebservice/rest/article/list/3/1/datetime",
            crossDomain: true,
            dataType: "json",
            cache: false,
            success: function (data) {
            	allPost(data, "pack2");
            }
        });
        /*$.ajax({
            type: "get",
            url: "/restwebservice/rest/article/list/2/0/datetime",
            crossDomain: true,
            dataType: "json",
            cache: false,
            success: function (data) {
            	allPost(data, "popular")
            }
        });*/
    });


