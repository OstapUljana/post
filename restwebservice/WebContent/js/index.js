$(document).ready(function() {
	authorization();
});
	
function authorization() {
    $('#login').submit(function(e) {
        e.preventDefault();
        var email = $('#email').val();
        var password = $('#password').val();
        $.ajax({
            type: 'post',
            url: '/restwebservice/rest/session/log',
            crossDomain: true,
            data: {'email': email, 'password': password},
            response: 'text', // response type
            error: function (data) {
                $('#login_message').html(data.responseText);
            },
            statusCode: {
                // HTTP 307 - redirect
                307: function (data) {
                    document.location.href = data.responseText;
                }
            }

        });       
        return false;
    });
}

    $('#navbarHeader').ready(function () {
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
        $('#navbarHeader').html(
                "<a href='javascript:void(0)' id = 'logout_button'>Вийти</a></li>"+
                "<div id='hellouser'>Доброго дня, " + name + "</div>"
        );

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
        $(('#navbarHeader')).html(
                "<a href='javascript:void(0)' id = 'enter'>Увійти</a>" 
        );
        $("#enter").click(function () {
            $("#login").slideToggle("slow");
            $(this).toggleClass("active");
        });
    }
    
    
    
  
    function fillBookContent(data, clas) {
        var i = 0;
        if (data != null) {
            $("." + clas).each(function () {
                $(this).html("<div class = 'b-title'>"+
                		"<a><span class = 'title'>" + data[i].title + "</span></a></div>"+
                		 "<div class = 'b-author'><a href = '#'><span class = 'author'>"
                		+ data[i].idUsers + "</span></a></div>"+
                		"<div><a>"+data[i].text+"</a></div>");
                i++
            });
        }
    }
        

        $('.row').ready(function () {
            $.ajax({
                type: "get",
                url: "/restwebservice/rest/article/list/2/1/datetime",
                crossDomain: true,
                dataType: "json",
                cache: false,
                success: function (data) {
                    fillBookContent(data, "new");
                }
            });
            $.ajax({
                type: "get",
                url: "/restwebservice/rest/article/list/2/0/datetime",
                crossDomain: true,
                dataType: "json",
                cache: false,
                success: function (data) {
                    fillBookContent(data, "popular")
                }
            });
        });


          